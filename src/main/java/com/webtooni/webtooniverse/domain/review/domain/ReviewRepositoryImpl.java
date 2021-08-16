package com.webtooni.webtooniverse.domain.review.domain;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    public List<ReviewResponseDto> getBestOrNewReview(ReviewStatus reviewStatus) {
        if (reviewStatus == ReviewStatus.BEST) {
            return getReviewResponseQuery().orderBy(review.likeCount.desc()).fetch();
        } else if (reviewStatus == ReviewStatus.NEW) {
            return getReviewResponseQuery().orderBy(review.likeCount.desc()).fetch();
        } else {
            throw new IllegalArgumentException("리뷰 상태가 올바르지 않습니다.");
        }
    }

    @Override
    public List<ReviewResponseDto> getNewReviewWithPageable(Pageable pageable) {
        List<ReviewResponseDto> reviewResponseDtos = getReviewResponseQuery()
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

    //웹툰 리스트에 장르 추가
    private List<ReviewResponseDto> addGenreToWebtoonList(List<ReviewResponseDto> reviewResponseDtos) {
        List<Long> webtoonIdList = new ArrayList<>();
        reviewResponseDtos.forEach(w -> webtoonIdList.add(w.getToonId()));

        List<Tuple> webtoonGenreTuples = jpaQueryFactory
            .select(webtoonGenre.webtoon.id, webtoonGenre.genre.genreType)
            .from(webtoonGenre)
            .join(webtoonGenre.webtoon)
            .join(webtoonGenre.genre)
            .where(webtoonGenre.webtoon.id.in(webtoonIdList))
            .fetch();

        for (ReviewResponseDto reviewResponseDto : reviewResponseDtos) {
            for (Tuple webtoonGenre : webtoonGenreTuples) {
                if (reviewResponseDto.getToonId()
                    .equals(webtoonGenre.get(QWebtoonGenre.webtoonGenre.webtoon.id))) {
                    reviewResponseDto
                        .addGenre(webtoonGenre.get(QWebtoonGenre.webtoonGenre.genre.genreType));
                }
            }
        }
        return reviewResponseDtos;
    }

    //reviewResponseDto 기본 query
    private JPAQuery<ReviewResponseDto> getReviewResponseQuery() {
        JPAQuery<ReviewResponseDto> jpaQuery = jpaQueryFactory.select(Projections.constructor(ReviewResponseDto.class,
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
                .limit(10);

        return jpaQuery;
    }

}
