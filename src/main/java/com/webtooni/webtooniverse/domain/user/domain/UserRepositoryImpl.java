package com.webtooni.webtooniverse.domain.user.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.webtooni.webtooniverse.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    //베스트리뷰어
    public List<User> getBestReviewer(){
        return jpaQueryFactory.selectFrom(user)
                .orderBy(user.userGrade.asc())
                .limit(5)
                .fetch();
    }
}
