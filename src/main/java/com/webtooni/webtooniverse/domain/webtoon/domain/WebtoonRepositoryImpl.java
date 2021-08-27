package com.webtooni.webtooniverse.domain.webtoon.domain;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.querydsl.core.group.GroupBy.set;
import static com.querydsl.core.types.ExpressionUtils.count;
import static com.webtooni.webtooniverse.domain.myList.QMyList.myList;
import static com.webtooni.webtooniverse.domain.review.domain.QReview.review;
import static com.webtooni.webtooniverse.domain.user.domain.QUserGenre.userGenre;
import static com.webtooni.webtooniverse.domain.webtoon.domain.QWebtoon.webtoon;
import static com.webtooni.webtooniverse.domain.webtoonGenre.QWebtoonGenre.webtoonGenre;

import com.querydsl.core.Tuple;
import com.querydsl.core.group.Group;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.RankTotalResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.SimilarGenreToonDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import com.webtooni.webtooniverse.domain.webtoonGenre.QWebtoonGenre;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WebtoonRepositoryImpl implements WebtoonRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    LocalDateTime midnight = LocalDateTime.of(
        LocalDate.now(), LocalTime.of(0, 0, 0));

    //비슷한 장르의 웹툰 추천
    public List<SimilarGenreToonDto> findSimilarWebtoonByGenre(String genre, Webtoon webtoon) {
        return queryFactory.select(Projections.constructor(SimilarGenreToonDto.class,
            webtoonGenre.webtoon.id.as("toonId"),
            webtoonGenre.webtoon.toonImg,
            webtoonGenre.webtoon.toonTitle,
            webtoonGenre.webtoon.toonAuthor,
            webtoonGenre.webtoon.toonPlatform,
            webtoonGenre.webtoon.toonWeekday,
            webtoonGenre.webtoon.toonAvgPoint,
            webtoonGenre.webtoon.totalPointCount
        ))
            .from(webtoonGenre)
            .join(webtoonGenre.genre)
            .on(webtoonGenre.genre.genreType.eq(genre), webtoonGenre.webtoon.ne(webtoon),
                webtoonGenre.genre.id.ne(1L), webtoonGenre.genre.id.ne(2L),
                webtoonGenre.genre.id.ne(3L))
            .limit(20)
            .fetch();
    }

    //이번주 웹투니버스 종합순위
    public List<RankTotalResponseDto> getTotalRank() {

        Map<Webtoon, Group> transform = queryFactory
            .from(review)
            .join(review.webtoon, webtoon)
            .where(review.starCreateDate.between(midnight.minusDays(7), midnight))
            .join(webtoonGenre)
            .on(webtoonGenre.webtoon.id.eq(webtoon.id))
            .join(webtoonGenre.genre)
            .transform(
                groupBy(webtoon)
                    .as(set(webtoonGenre.genre.genreType), list(review.userPointNumber)));

        List<RankTotalResponseDto> rankTotalResponseDtoList = new ArrayList<>();
        for (Entry<Webtoon, Group> entry : transform.entrySet()) {
            if (entry.getValue().getList(review.userPointNumber).size() >= 4) {
                RankTotalResponseDto rankTotalResponseDto = new RankTotalResponseDto(
                    entry.getKey(),
                    entry.getValue().getSet(webtoonGenre.genre.genreType),
                    entry.getValue().getList(review.userPointNumber));
                rankTotalResponseDtoList.add(rankTotalResponseDto);
            }
        }
        return rankTotalResponseDtoList;
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
            .orderBy(webtoon.toonAvgPoint.desc())
            .where(webtoon.toonPlatform.eq("카카오"))
            .limit(10)
            .fetch();
    }

    //베스트 리뷰어 찾기
    @Override
    public User findBestReviewer() {

        return queryFactory.select(review.user)
            .from(review)
//            .where(review.createDate.between(midnight.minusDays(7), midnight))
            .groupBy(review.user)
            .orderBy(review.user.count().desc())
            .limit(1)
            .fetchOne();
    }

    // 베스트 리뷰어, 리뷰 수, 좋아요 수
    @Override
    public List<BestReviewerResponseDto> findBestReviewerForMain() {
        List<BestReviewerResponseDto> bestReviewerResponseDtoList = queryFactory
            .select(Projections.fields(BestReviewerResponseDto.class, review.user,
                review.user.count().as("reviewCount"),
                review.likeCount.sum().as("likeCount")))
            .from(review)
            .where(review.createDate.isNotNull())
            .groupBy(review.user, review.user)
            .orderBy(review.user.count().desc())
            .limit(5)
            .fetch();
        return bestReviewerResponseDtoList;
    }

    //베스트 리뷰어 추천 웹툰
    @Override
    public List<WebtoonAndGenreResponseDto> findBestReviewerWebtoon(User bestReviewer) {
        Map<Webtoon, List<String>> webtoonGenreList = queryFactory
            .from(review)
            .join(review.webtoon, webtoon)
            .join(webtoonGenre)
            .on(review.webtoon.id.eq(webtoonGenre.webtoon.id))
            .join(webtoonGenre.genre)
            .where(review.user.eq(bestReviewer))
            .orderBy(review.userPointNumber.desc())
            .limit(20)
            .transform(groupBy(review.webtoon).as(list(webtoonGenre.genre.genreType)));
        return mappingMapToDto(webtoonGenreList);
    }


    //유저 취향 웹툰 추천
    @Override
    public List<Webtoon> findUserGenreWebtoon(User user) {
        List<Genre> genres = queryFactory.select(userGenre.genre)
            .from(userGenre)
            .where(userGenre.user.eq(user))
            .fetch();
        //유저가 취향선택 안되었을 때 웹투니버스 높은 점수순으로 추천
        if (genres.size() == 0) {
            return queryFactory
                .selectFrom(webtoon)
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(10)
                .fetch();
        }

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

        if (webtoons.size() == 0) {
            return addGenreToWebtoonList(findUserGenreWebtoon(user));
        }

        User similarUser = queryFactory.select(review.user)
            .from(review)
            .where(review.webtoon.in(webtoons), review.user.ne(user),
                review.userPointNumber.goe(3.5))
            .groupBy(review.user)
            .orderBy(review.user.count().desc())
            .fetchFirst();

        if (similarUser == null) {
            return addGenreToWebtoonList(findUserGenreWebtoon(user));
        }

        Map<Webtoon, List<String>> webtoonGenreList = queryFactory
            .from(review)
            .join(review.webtoon)
            .join(webtoonGenre)
            .on(review.webtoon.id.eq(webtoonGenre.webtoon.id))
            .join(webtoonGenre.genre)
            .where(review.user.eq(similarUser), review.userPointNumber.goe(3.5),
                review.webtoon.notIn(webtoons))
            .orderBy(review.userPointNumber.desc())
            .limit(5)
            .transform(groupBy(review.webtoon).as(list(webtoonGenre.genre.genreType)));
        return mappingMapToDto(webtoonGenreList);
    }

    //완결 웹툰 추천
    @Override
    public List<WebtoonAndGenreResponseDto> findFinishedWebtoon() {
        Map<Webtoon, List<String>> webtoonGenreList = queryFactory
            .from(webtoon)
            .join(webtoonGenre)
            .on(webtoon.id.eq(webtoonGenre.webtoon.id))
            .join(webtoonGenre.genre)
            .where(webtoon.finished.eq(true))
            .orderBy(webtoon.toonAvgPoint.desc())
            .limit(15)
            .transform(groupBy(webtoon).as(list(webtoonGenre.genre.genreType)));
        return mappingMapToDto(webtoonGenreList);
    }


    @Override
    public List<WebtoonAndGenreResponseDto> findMyListWebtoon(String username) {
        Map<Webtoon, List<String>> webtoonGenreList = queryFactory
            .from(myList)
            .join(myList.webtoon)
            .join(webtoonGenre)
            .on(myList.webtoon.id.eq(webtoonGenre.webtoon.id))
            .on(webtoonGenre.genre.id.ne(1L))
            .on(webtoonGenre.genre.id.ne(2L))
            .on(webtoonGenre.genre.id.ne(3L))
            .join(webtoonGenre.genre)
            .where(myList.user.userName.eq(username))
            .transform(groupBy(myList.webtoon).as(list(webtoonGenre.genre.genreType)));
        return mappingMapToDto(webtoonGenreList);
    }

    //웹툰 검색
    @Override
    public List<WebtoonAndGenreResponseDto> findSearchedWebtoon(String keyword) {
        Map<Webtoon, List<String>> webtoonGenreList = queryFactory
            .from(webtoon)
            .join(webtoonGenre)
            .on(webtoon.id.eq(webtoonGenre.webtoon.id))
            .join(webtoonGenre.genre)
            .where(webtoon.toonTitle.contains(keyword))
            .orderBy(webtoon.toonTitle.asc())
            .transform(groupBy(webtoon).as(list(webtoonGenre.genre.genreType)));
        return mappingMapToDto(webtoonGenreList);
    }

    //리뷰없는 웹툰 추천
    public List<Webtoon> getUnreviewedList(){
        return queryFactory.selectFrom(webtoon)
            .where(webtoon.reviewCount.eq(0))
            .limit(10)
            .fetch();
    }

    @Override
    public List<Review> br(Long userId) {
        List<Long> webtoonIds = queryFactory.select(review.webtoon.id)
            .from(review)
            .where(review.user.id.eq(userId))
            .fetch();

        return queryFactory.selectFrom(review)
            .where(review.webtoon.id.in(webtoonIds))
            .fetch();
    }


    private List<WebtoonAndGenreResponseDto> addGenreToWebtoonList(List<Webtoon> webtoons) {
        List<WebtoonAndGenreResponseDto> webtoonAndGenreResponseDtos = webtoons.stream()
            .map(WebtoonAndGenreResponseDto::new)
            .collect(Collectors.toList());

        List<Tuple> webtoonGenreTuples = queryFactory
            .select(webtoonGenre.webtoon.id, webtoonGenre.genre.genreType)
            .from(webtoonGenre)
            .join(webtoonGenre.webtoon)
            .join(webtoonGenre.genre)
            .where(webtoonGenre.webtoon.in(webtoons))
            .fetch();

        for (WebtoonAndGenreResponseDto webtoonAndGenreResponseDto : webtoonAndGenreResponseDtos) {
            for (Tuple webtoonGenre : webtoonGenreTuples) {
                if (webtoonAndGenreResponseDto.getToonId()
                    .equals(webtoonGenre.get(QWebtoonGenre.webtoonGenre.webtoon.id))) {
                    webtoonAndGenreResponseDto
                        .addGenre(webtoonGenre.get(QWebtoonGenre.webtoonGenre.genre.genreType));
                }
            }
        }
        return webtoonAndGenreResponseDtos;
    }

    private List<WebtoonAndGenreResponseDto> mappingMapToDto(
        Map<Webtoon, List<String>> WebtoonGenreMap) {
        return WebtoonGenreMap.entrySet().stream()
            .map(entry -> new WebtoonAndGenreResponseDto(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

    }

}
