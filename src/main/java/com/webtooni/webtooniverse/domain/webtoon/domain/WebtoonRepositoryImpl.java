package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

import static com.webtooni.webtooniverse.domain.review.domain.QReview.review;
import static com.webtooni.webtooniverse.domain.userGenre.QUserGenre.userGenre;
import static com.webtooni.webtooniverse.domain.webtoon.domain.QWebtoon.*;
import static com.webtooni.webtooniverse.domain.webtoonGenre.QWebtoonGenre.webtoonGenre;


@RequiredArgsConstructor
public class WebtoonRepositoryImpl implements WebtoonRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    //금주의 웹툰 평론가 추천
    @Override
    public List<Webtoon> findBestReviewerWebtoon(LocalDateTime startDate){
        User bestReviewer = jpaQueryFactory.select(review.user)
                .from(review)
                .where(review.createDate.between(startDate, LocalDateTime.now()))
                .groupBy(review.user)
                .orderBy(review.user.count().desc())
                .limit(1)
                .fetchOne();

        return jpaQueryFactory.select(review.webtoon)
                .from(review)
                .where(review.user.eq(bestReviewer))
                .orderBy(review.userPointNumber.desc())
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
                .limit(20)
                .fetch();
    }

    //나와 비슷한 취향을 가진 사용자가 많이 본 웹툰
    @Override
    public List<Webtoon> findSimilarUserWebtoon(User user){
        List<Webtoon> webtoons = jpaQueryFactory.select(review.webtoon)
                .from(review)
                .where(review.user.eq(user), review.userPointNumber.goe(3.5))
                .fetch();

        User similarUser = jpaQueryFactory.select(review.user)
                .from(review)
                .where(review.webtoon.in(webtoons), review.user.ne(user), review.userPointNumber.goe(3.5))
                .groupBy(review.user)
                .orderBy(review.user.count().desc())
                .fetchFirst();

        if (similarUser == null){
            return findUserGenreWebtoon(user);
        }

        return jpaQueryFactory.select(review.webtoon)
                .from(review)
                .where(review.user.eq(similarUser),review.userPointNumber.goe(4.0), review.webtoon.notIn(webtoons))
                .orderBy(review.webtoon.toonAvgPoint.desc())
                .limit(5)
                .fetch();
    }

    //완결 웹툰 추천
    @Override
    public List<Webtoon> findFinishedWebtoon() {
        return jpaQueryFactory.selectFrom(webtoon)
                .where(webtoon.finished.eq(true))
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(20)
                .fetch();
    }



}
