package com.webtooni.webtooniverse.domain.user.domain;

import static com.webtooni.webtooniverse.domain.review.domain.QReview.review;
import static com.webtooni.webtooniverse.domain.user.domain.QUserGenre.userGenre;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 베스트 리뷰어를 조회합니다.
     * @return 베스트 리뷰어 목록
     */
    public List<User> getBestReviewer() {
        return jpaQueryFactory.select(review.user)
            .from(review)
            .groupBy(review.user)
            .orderBy(review.user.count().desc())
            .limit(5)
            .fetch();
    }

    /**
     * 유저가 선호하는 장르를 조회합니다.
     * @param userId 유저 id
     * @return 선호 장르 목록
     */
    @Override
    public List<String> getUserGenre(Long userId) {
        return jpaQueryFactory.select(userGenre.genre.genreType)
            .from(userGenre)
            .where(userGenre.user.id.eq(userId))
            .fetch();
    }

    /**
     * 유조가 선호하는 장르를 조회합니다.
     * @param userName 유저 이름
     * @return 선호 장르 목록
     */
    @Override
    public List<String> getUserGenreByUserName(String userName) {
        return jpaQueryFactory.select(userGenre.genre.genreType)
            .from(userGenre)
            .where(userGenre.user.userName.eq(userName))
            .fetch();
    }
}
