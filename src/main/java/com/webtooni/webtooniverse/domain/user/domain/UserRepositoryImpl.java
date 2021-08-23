package com.webtooni.webtooniverse.domain.user.domain;

import static com.webtooni.webtooniverse.domain.review.domain.QReview.review;
import static com.webtooni.webtooniverse.domain.user.domain.QUserGenre.userGenre;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    //베스트리뷰어
    public List<User> getBestReviewer() {
        return jpaQueryFactory.select(review.user)
            .from(review)
            .groupBy(review.user)
            .orderBy(review.user.count().desc())
            .limit(5)
            .fetch();
    }

    @Override
    public List<String> getUserGenre(Long userId) {
        return jpaQueryFactory.select(userGenre.genre.genreType)
            .from(userGenre)
            .where(userGenre.user.id.eq(userId))
            .fetch();
    }

    @Override
    public List<String> getUserGenreByUserName(String userName) {
        return jpaQueryFactory.select(userGenre.genre.genreType)
            .from(userGenre)
            .where(userGenre.user.userName.eq(userName))
            .fetch();
    }
}
