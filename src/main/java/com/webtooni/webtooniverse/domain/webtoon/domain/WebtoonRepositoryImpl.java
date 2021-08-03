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

  
}
