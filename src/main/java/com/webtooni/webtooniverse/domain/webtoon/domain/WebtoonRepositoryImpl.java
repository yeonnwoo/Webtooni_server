package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoonGenre.QWebtoonGenre;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.webtooni.webtooniverse.domain.review.domain.QReview.review;
import static com.webtooni.webtooniverse.domain.user.domain.QUserGenre.userGenre;
import static com.webtooni.webtooniverse.domain.webtoon.domain.QWebtoon.webtoon;
import static com.webtooni.webtooniverse.domain.webtoonGenre.QWebtoonGenre.webtoonGenre;

@RequiredArgsConstructor
public class WebtoonRepositoryImpl implements WebtoonRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Webtoon> findSimilarWebtoonByGenre(String genre, Webtoon webtoon) {
        QWebtoonGenre wg = new QWebtoonGenre("wg");

        return queryFactory
                .select(wg.webtoon)
                .from(wg)
                .join(wg.genre)
                .on(wg.genre.genreType.eq(genre), wg.webtoon.ne(webtoon))
                .orderBy(NumberExpression.random().asc())
                .limit(2)
                .fetch();
    }

    //이번달 웹투니버스 종합순위
    public List<Webtoon> getTotalRank() {
        return queryFactory.selectFrom(webtoon)
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(10)
                .fetch();

    }

    //네이버 웹툰 Top10
    public List<Webtoon> getNaverRank() {
        return queryFactory.selectFrom(webtoon)
                .where(webtoon.toonPlatform.eq("네이버"))
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(10)
                .fetch();
    }

    //카카오 웹툰 Top10
    public List<Webtoon> getKakaoRank() {
        return queryFactory.selectFrom(webtoon)
                .where(webtoon.toonPlatform.eq("카카오"))
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(10)
                .fetch();
    }

    @Override
    public List<Webtoon> findBestReviewerWebtoon(LocalDateTime startDate) {
        User bestReviewer = queryFactory.select(review.user)
                .from(review)
                .where(review.createDate.between(startDate, LocalDateTime.now()))
                .groupBy(review.user)
                .orderBy(review.user.count().desc())
                .limit(1)
                .fetchOne();

        return queryFactory.select(review.webtoon)
                .from(review)
                .where(review.user.eq(bestReviewer))
                .orderBy(review.userPointNumber.desc())
                .limit(5)
                .fetch();
    }

    //유저 취향 웹툰 추천
    @Override
    public List<Webtoon> findUserGenreWebtoon(User user) {
        List<Genre> genres = queryFactory.select(userGenre.genre)
                .from(userGenre)
                .where(userGenre.user.eq(user))
                .fetch();


        return queryFactory.select(webtoonGenre.webtoon)
                .from(webtoonGenre)
                .where(webtoonGenre.genre.in(genres))
                .limit(20)
                .fetch();
    }

    //나와 비슷한 취향을 가진 사용자가 많이 본 웹툰
    @Override
    public List<Webtoon> findSimilarUserWebtoon(User user) {
        List<Webtoon> webtoons = queryFactory.select(review.webtoon)
                .from(review)
                .where(review.user.eq(user), review.userPointNumber.goe(3.5))
                .fetch();

        User similarUser = queryFactory.select(review.user)
                .from(review)
                .where(review.webtoon.in(webtoons), review.user.ne(user), review.userPointNumber.goe(3.5))
                .groupBy(review.user)
                .orderBy(review.user.count().desc())
                .fetchFirst();

        if (similarUser == null) {
            return findUserGenreWebtoon(user);
        }

        return queryFactory.select(review.webtoon)
                .from(review)
                .where(review.user.eq(similarUser), review.userPointNumber.goe(4.0), review.webtoon.notIn(webtoons))
                .orderBy(review.webtoon.toonAvgPoint.desc())
                .limit(5)
                .fetch();
    }

    //완결 웹툰 추천
    @Override
    public List<Webtoon> findFinishedWebtoon() {
        return queryFactory.selectFrom(webtoon)
                .where(webtoon.finished.eq(true))
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(20)
                .fetch();
    }


    // 베스트 리뷰어, 리뷰 수, 좋아요 수
    @Override
    public List<BestReviewerResponseDto> findBestReviewerForMain() {
        List<BestReviewerResponseDto> dtos = queryFactory
                .select(Projections.fields(BestReviewerResponseDto.class,
                        review.user, review.user.count().as("reviewCount")))
                .from(review)
                .groupBy(review.user, review.user)
                .orderBy(review.user.count().desc())
                .limit(5)
                .fetch();

        List<User> users = new ArrayList<>();
        for (BestReviewerResponseDto dto : dtos) {
            users.add(dto.getUser());
        }

        List<Review> reviews = queryFactory.selectFrom(review)
                .where(review.user.in(users))
                .fetch();

        for (int i = 0; i < dtos.size(); i++) {
            int sum = 0;
            for (Review review : reviews) {
                if (review.getUser() == users.get(i)) {
                    sum += review.getLikeCount();
                }
            }
            dtos.get(i).setLikeCount(sum);
        }
        return dtos;
    }
}
