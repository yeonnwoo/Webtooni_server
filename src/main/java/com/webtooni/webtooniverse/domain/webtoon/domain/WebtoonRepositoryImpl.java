package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import com.webtooni.webtooniverse.domain.webtoonGenre.QWebtoonGenre;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import static com.webtooni.webtooniverse.domain.myList.QMyList.myList;
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
                .limit(10)
                .fetch();
    }

    //이번달 웹투니버스 종합순위
    public List<WebtoonAndGenreResponseDto> getTotalRank() {
        List<Webtoon> webtoons = queryFactory
                .selectFrom(webtoon)
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(10)
                .fetch();
        return addGenreToWebtoonList(webtoons);
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

    //베스트 리뷰어 찾기
    @Override
    public User findBestReviewer(LocalDateTime startDate) {
        return queryFactory.select(review.user)
                .from(review)
                .where(review.createDate.between(startDate, LocalDateTime.now()))
                .groupBy(review.user)
                .orderBy(review.user.count().desc())
                .limit(1)
                .fetchOne();
    }

    //베스트 리뷰어 추천 웹툰
    @Override
    public List<WebtoonAndGenreResponseDto> findBestReviewerWebtoon(User bestReviewer) {
        List<Webtoon> webtoons = queryFactory.select(review.webtoon)
                .from(review)
                .where(review.user.eq(bestReviewer))
                .orderBy(review.userPointNumber.desc())
                .limit(5)
                .fetch();

        return addGenreToWebtoonList(webtoons);
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
    public List<WebtoonAndGenreResponseDto> findSimilarUserWebtoon(User user) {
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
            return addGenreToWebtoonList(findUserGenreWebtoon(user));
        }

        List<Webtoon> webtoonList = queryFactory.select(review.webtoon)
                .from(review)
                .where(review.user.eq(similarUser), review.userPointNumber.goe(4.0), review.webtoon.notIn(webtoons))
                .orderBy(review.webtoon.toonAvgPoint.desc())
                .limit(5)
                .fetch();

        return addGenreToWebtoonList(webtoonList);
    }

    //완결 웹툰 추천
    @Override
    public List<WebtoonAndGenreResponseDto> findFinishedWebtoon() {
        List<Webtoon> webtoons = queryFactory.selectFrom(webtoon)
                .where(webtoon.finished.eq(true))
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(15)
                .fetch();

        return addGenreToWebtoonList(webtoons);
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

    @Override
    public List<Webtoon> findMyListWebtoon(User user) {
        return queryFactory.select(myList.webtoon)
                .from(myList)
                .where(myList.user.eq(user))
                .fetch();
    }

    @Override
    public List<WebtoonAndGenreResponseDto> findSearchedWebtoon(String keyword) {
        List<Webtoon> webtoons = queryFactory.selectFrom(webtoon)
                .where(webtoon.toonTitle.contains(keyword))
                .limit(20)
                .fetch();
        return addGenreToWebtoonList(webtoons);
    }


    private List<WebtoonAndGenreResponseDto> addGenreToWebtoonList(List<Webtoon> webtoons) {
        List<WebtoonAndGenreResponseDto> webtoonAndGenreResponseDtos = webtoons.stream()
                .map(WebtoonAndGenreResponseDto::new)
                .collect(Collectors.toList());

        List<Tuple> webtoonGenreTuples = queryFactory.select(webtoonGenre.webtoon.id, webtoonGenre.genre.genreType)
                .from(webtoonGenre)
                .join(webtoonGenre.webtoon)
                .join(webtoonGenre.genre)
                .where(webtoonGenre.webtoon.in(webtoons))
                .fetch();

        for (WebtoonAndGenreResponseDto webtoonAndGenreResponseDto : webtoonAndGenreResponseDtos) {
            for (Tuple webtoonGenre : webtoonGenreTuples) {
                if (webtoonAndGenreResponseDto.getId().equals(webtoonGenre.get(QWebtoonGenre.webtoonGenre.webtoon.id))) {
                    webtoonAndGenreResponseDto.addGenre(webtoonGenre.get(QWebtoonGenre.webtoonGenre.genre.genreType));
                }
            }
        }
        return webtoonAndGenreResponseDtos;
    }


}
