package com.webtooni.webtooniverse.domain.review.domain;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.webtooni.webtooniverse.domain.genre.domain.QGenre.genre;
import static com.webtooni.webtooniverse.domain.review.domain.QReview.review;
import static com.webtooni.webtooniverse.domain.user.domain.QUser.user;
import static com.webtooni.webtooniverse.domain.webtoon.domain.QWebtoon.webtoon;
import static com.webtooni.webtooniverse.domain.webtoonGenre.QWebtoonGenre.webtoonGenre;

import com.querydsl.core.Tuple;
import com.querydsl.core.group.Group;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtooni.webtooniverse.domain.genre.domain.QGenre;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewWebtoonGenre;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import com.webtooni.webtooniverse.domain.webtoonGenre.QWebtoonGenre;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    //리뷰 베스트순
    public List<ReviewResponseDto> getBestOrNewReview(ReviewStatus reviewStatus) {
        if (reviewStatus == ReviewStatus.BEST) {
            return getReviewResponseQuery().orderBy(review.likeCount.desc()).fetch();
        } else if (reviewStatus == ReviewStatus.NEW) {
            return getReviewResponseQuery().orderBy(review.createDate.desc()).fetch();
        } else {
            throw new IllegalArgumentException("리뷰 상태가 올바르지 않습니다.");
        }
    }

    //리뷰 최신순 pageable
    @Override
    public List<ReviewResponseDto> getNewReviewWithPageable(Pageable pageable) {
        List<ReviewResponseDto> reviewResponseDtos = getReviewResponseQuery()
            .offset(pageable.getOffset())
            .where(review.reviewContent.isNotNull())
            .orderBy(review.createDate.desc())
            .limit(pageable.getPageSize())
            .fetch();

        return addGenreToWebtoonList(reviewResponseDtos);
    }

    @Override
    public List<ReviewResponseDto> getBestReviewWithPageable(Pageable pageable) {
        List<ReviewResponseDto> reviewResponseDtos = getReviewResponseQuery()
            .offset(pageable.getOffset())
            .where(review.reviewContent.isNotNull())
            .orderBy(review.likeCount.desc())
            .limit(pageable.getPageSize())
            .fetch();

        return addGenreToWebtoonList(reviewResponseDtos);
    }

    @Override
    public List<Review> findMyReviews(String userName) {
        return jpaQueryFactory.selectFrom(review)
            .where(review.user.userName.eq(userName).and(review.createDate.isNotNull()))
            .fetch();
    }

    @Override
    public List<ReviewWebtoonGenre> findMyReviewsAndGenre(String userName) {
        Map<Review, List<String>> transform = jpaQueryFactory
            .from(review)
            .join(review.webtoon, webtoon)
            .fetchJoin()
            .join(webtoonGenre)
            .fetchJoin()
            .on(webtoon.id.eq(webtoonGenre.webtoon.id))
            .on(webtoonGenre.genre.id.ne(1L))
            .on(webtoonGenre.genre.id.ne(2L))
            .on(webtoonGenre.genre.id.ne(3L))
            .join(webtoonGenre.genre, genre)
            .where(review.user.userName.eq(userName).and(review.createDate.isNotNull()))
            .transform(groupBy(review).as(list(webtoonGenre.genre.genreType)));

        return transform.entrySet().stream()
            .map(entry -> new ReviewWebtoonGenre(entry.getKey(),entry.getValue()))
            .collect(Collectors.toList());

    }

    //웹툰 리스트에 장르 추가
    private List<ReviewResponseDto> addGenreToWebtoonList(
        List<ReviewResponseDto> reviewResponseDtos) {
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
        JPAQuery<ReviewResponseDto> jpaQuery = jpaQueryFactory.select(
            Projections.constructor(ReviewResponseDto.class,
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
