package com.webtooni.webtooniverse.domain.user.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.webtooni.webtooniverse.domain.review.domain.QReview.review;
import static com.webtooni.webtooniverse.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    //베스트리뷰어
    public List<User> getBestReviewer(){
        return jpaQueryFactory.select(review.user)
                .from(review)
                .groupBy(review.user)
                .orderBy(review.user.count().desc())
                .limit(5)
                .fetch();
    }
}
