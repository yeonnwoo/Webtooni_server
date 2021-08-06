package com.webtooni.webtooniverse.domain.review.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.webtooni.webtooniverse.domain.review.domain.QReview.review;


@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    //리뷰 베스트순
    public List<Review> getBestReview(){
        return jpaQueryFactory.selectFrom(review)
                .orderBy(review.userPointNumber.desc())
                .limit(5)
                .fetch();
    }

    //리뷰 최신순
    public List<Review> getNewReview(){
        return jpaQueryFactory.selectFrom(review)
                .orderBy(review.createDate.desc())
                .limit(5)
                .fetch();
    }
}
