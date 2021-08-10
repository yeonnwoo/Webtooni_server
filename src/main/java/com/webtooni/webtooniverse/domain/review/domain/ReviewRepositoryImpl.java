package com.webtooni.webtooniverse.domain.review.domain;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewBestResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewNewResponseDto;
import lombok.RequiredArgsConstructor;
import java.util.List;
import static com.webtooni.webtooniverse.domain.review.domain.QReview.review;
import static com.webtooni.webtooniverse.domain.user.domain.QUser.user;
import static com.webtooni.webtooniverse.domain.webtoon.domain.QWebtoon.webtoon;


@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    //리뷰 베스트순
    public List<ReviewBestResponseDto> getBestReview() {
        return jpaQueryFactory
                .select(Projections.constructor(ReviewBestResponseDto.class,
                        review.user.id,
                        review.user.userImg,
                        review.user.userName,
                        review.userPointNumber,
                        review.reviewContent,
                        review.webtoon.toonTitle,
                        review.user.userGrade,
                        review.webtoon.toonImg,
                        review.webtoon.toonAuthor,
                        review.webtoon.toonPlatform,
                        review.webtoon.toonWeekday,
                        review.webtoon.finished
                ))
                .from(review)
                .innerJoin(review.user, user)
                .innerJoin(review.webtoon, webtoon)
                .orderBy(review.userPointNumber.desc())
                .limit(10)
                .fetch();

    }

    //리뷰 최신순
    public List<ReviewNewResponseDto> getNewReview() {
        return jpaQueryFactory
                .select(Projections.constructor(ReviewNewResponseDto.class,
                        review.user.id,
                        review.user.userImg,
                        review.user.userName,
                        review.userPointNumber,
                        review.reviewContent,
                        review.webtoon.toonTitle,
                        review.user.userGrade,
                        review.webtoon.toonImg,
                        review.webtoon.toonAuthor,
                        review.webtoon.toonPlatform,
                        review.webtoon.toonWeekday,
                        review.webtoon.finished
                ))
                .from(review)
                .innerJoin(review.user, user)
                .innerJoin(review.webtoon, webtoon)
                .orderBy(review.createDate.desc())
                .fetch();

    }

    @Override
    public List<Review> findMyReviews(User user) {
        return jpaQueryFactory.selectFrom(review)
                .where(review.user.eq(user))
                .fetch();
    }
}
