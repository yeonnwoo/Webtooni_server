package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtooni.webtooniverse.domain.join.Genre;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

import static com.webtooni.webtooniverse.domain.join.QUserGenre.userGenre;
import static com.webtooni.webtooniverse.domain.join.QWebtoonGenre.webtoonGenre;
import static com.webtooni.webtooniverse.domain.review.domain.QReview.review;
import static com.webtooni.webtooniverse.domain.webtoon.domain.QWebtoon.*;


@RequiredArgsConstructor
public class WebtoonRepositoryImpl implements WebtoonRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    //금주의 웹툰 평론가 추천
    @Override
    public List<Webtoon> findBestReviewerWebtoon(LocalDateTime startDate, LocalDateTime now){
        User bestReviewer = jpaQueryFactory.select(review.user)
                .from(review)
                .where(review.createDate.between(startDate, now))
                .groupBy(review.user)
                .orderBy(review.user.count().desc())
                .limit(1)
                .fetchOne();

        return jpaQueryFactory.select(review.webtoon)
                .from(review)
                .where(review.user.eq(bestReviewer))
                .orderBy(review.webtoon.toonAvgPoint.desc())
                .limit(5)
                .fetch();
    }

    //유저 취향 웹툰 추천
    @Override
    public List<Webtoon> findUserGenreWebtoon(User user){
        List<Genre> genres = jpaQueryFactory.select(userGenre.genre)
                .from(userGenre)
                .where(userGenre.user.eq(user))
                .fetch();

        return jpaQueryFactory.select(webtoonGenre.webtoon)
                .from(webtoonGenre)
                .where(webtoonGenre.genre.in(genres))
//                .orderBy(NumberExpression.random().asc())
                .limit(30)
                .fetch();
    }

    //나와 비슷한 취향을 가진 사용자가 많이 본 웹툰
    @Override
    public List<Webtoon> findSimilarUserWebtoon(User user){
        List<String> webtoonTitles = jpaQueryFactory.select(review.webtoon.toonTitle)
                .from(review)
                .where(review.user.eq(user), review.userPointNumber.goe(4.0))
                .fetch();

        User similarUser = jpaQueryFactory.select(review.user)
                .from(review)
                .where(review.webtoon.toonTitle.in(webtoonTitles), review.user.ne(user))
                .groupBy(review.user)
                .orderBy(review.user.count().desc())
                .fetchFirst();

        if (similarUser!=null){
            return jpaQueryFactory.select(review.webtoon)
                    .from(review)
                    .where(review.user.id.eq(similarUser.getId()))
                    .orderBy(review.webtoon.toonAvgPoint.desc())
                    .limit(5)
                    .fetch();
        }
        return findUserGenreWebtoon(user);
    }

    //완결 웹툰 추천
    @Override
    public List<Webtoon> findFinishedWebtoon() {
        return jpaQueryFactory.selectFrom(webtoon)
                .where(webtoon.finished.eq(true))
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(5)
                .fetch();
    }

}
