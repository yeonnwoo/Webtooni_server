package com.webtooni.webtooniverse.domain.review.domain;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewResponseDto;
import com.webtooni.webtooniverse.domain.webtoonGenre.QWebtoonGenre;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static com.webtooni.webtooniverse.domain.review.domain.QReview.review;
import static com.webtooni.webtooniverse.domain.user.domain.QUser.user;
import static com.webtooni.webtooniverse.domain.webtoon.domain.QWebtoon.webtoon;
import static com.webtooni.webtooniverse.domain.webtoonGenre.QWebtoonGenre.webtoonGenre;


@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    //리뷰 베스트순
    public List<ReviewResponseDto> getBestReview() {
        List<ReviewResponseDto> reviewResponseDtos = jpaQueryFactory.select(Projections.constructor(ReviewResponseDto.class,
                review.user.id,
                review.user.userImg,
                review.user.userName,
                review.userPointNumber,
                review.reviewContent,
                review.webtoon.id,
                review.webtoon.toonTitle,
                review.user.userGrade,
                review.webtoon.toonImg,
                review.webtoon.toonAuthor,
                review.webtoon.toonPlatform,
                review.webtoon.toonWeekday,
                review.webtoon.finished,
                review.createDate,
                review.webtoon.toonAvgPoint,
                review.likeCount,
                review.id
        ))
                .from(review)
                .innerJoin(review.user, user)
                .innerJoin(review.webtoon, webtoon)
                .orderBy(review.likeCount.desc())
                .limit(10)
                .fetch();

        return addGenreToWebtoonList(reviewResponseDtos);

    }

    //리뷰 최신순
    public List<ReviewResponseDto> getNewReview() {
        List<ReviewResponseDto> reviewResponseDtos = jpaQueryFactory
                .select(Projections.constructor(ReviewResponseDto.class,
                        review.user.id,
                        review.user.userImg,
                        review.user.userName,
                        review.userPointNumber,
                        review.reviewContent,
                        review.likeCount,
                        review.createDate,
                        review.webtoon.id,
                        review.webtoon.toonTitle,
                        review.webtoon.toonImg,
                        review.webtoon.toonAuthor,
                        review.webtoon.toonPlatform,
                        review.webtoon.toonWeekday,
                        review.webtoon.finished,
                        review.createDate,
                        review.webtoon.toonAvgPoint,
                        review.likeCount,
                        review.id
                ))
                .from(review)
                .innerJoin(review.user, user)
                .innerJoin(review.webtoon, webtoon)
                .orderBy(review.createDate.desc())
                .limit(5)
                .fetch();

        return addGenreToWebtoonList(reviewResponseDtos);
    }

    @Override
    public List<ReviewResponseDto> getNewReviewWithPageable(Pageable pageable) {
        List<ReviewResponseDto> reviewResponseDtos = jpaQueryFactory.select(Projections.constructor(ReviewResponseDto.class,
                review.user.id,
                review.user.userImg,
                review.user.userName,
                review.userPointNumber,
                review.reviewContent,
                review.webtoon.id,
                review.webtoon.toonTitle,
                review.user.userGrade,
                review.webtoon.toonImg,
                review.webtoon.toonAuthor,
                review.webtoon.toonPlatform,
                review.webtoon.toonWeekday,
                review.webtoon.finished,
                review.createDate,
                review.webtoon.toonAvgPoint,
                review.likeCount,
                review.id
                ))
                .from(review)
                .innerJoin(review.user, user)
                .innerJoin(review.webtoon, webtoon)
                .orderBy(review.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return addGenreToWebtoonList(reviewResponseDtos);
    }

    @Override
    public List<Review> findMyReviews(Long userId) {
        return jpaQueryFactory.selectFrom(review)
                .where(review.user.id.eq(userId))
                .fetch();
    }

    private List<ReviewResponseDto> addGenreToWebtoonList(List<ReviewResponseDto> reviewResponseDtos) {
        List<Long> webtoonIdList = new ArrayList<>();
        reviewResponseDtos.stream().forEach(w -> webtoonIdList.add(w.getToonId()));

        List<Tuple> webtoonGenreTuples = jpaQueryFactory.select(webtoonGenre.webtoon.id, webtoonGenre.genre.genreType)
                .from(webtoonGenre)
                .join(webtoonGenre.webtoon)
                .join(webtoonGenre.genre)
                .where(webtoonGenre.webtoon.id.in(webtoonIdList))
                .fetch();

        for (ReviewResponseDto reviewResponseDto : reviewResponseDtos) {
            for (Tuple webtoonGenre : webtoonGenreTuples) {
                if (reviewResponseDto.getToonId().equals(webtoonGenre.get(QWebtoonGenre.webtoonGenre.webtoon.id))) {
                    reviewResponseDto.addGenre(webtoonGenre.get(QWebtoonGenre.webtoonGenre.genre.genreType));
                }
            }
        }
        return reviewResponseDtos;
    }
}
