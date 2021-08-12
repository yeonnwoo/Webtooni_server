package com.webtooni.webtooniverse.domain.webtoon.service;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.myList.MyListRepository;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.review.dto.response.WebtoonDetailReviewResponseDto;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.dto.response.UserInfoResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final MyListRepository myListRepository;

    //금주의 웹툰 평론가 추천
    public BestReviewerWebtoonResponseDto getBestReviewerWebtoon() {
        User bestReviewer = webtoonRepository.findBestReviewer(startDate());
        if (bestReviewer == null) {
            return null;
        }
        List<WebtoonAndGenreResponseDto> bestReviewerWebtoons = webtoonRepository.findBestReviewerWebtoon(bestReviewer);

//        List<WebtoonResponseDto> webtoonResponseDto = bestReviewerWebtoons.stream()
//                .map(WebtoonResponseDto::new)
//                .collect(Collectors.toList());
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto(bestReviewer);
        return new BestReviewerWebtoonResponseDto(userInfoResponseDto, bestReviewerWebtoons);
    }

    //유저 관심 장르 중 랜덤 추천
    public List<WebtoonResponseDto> getForUserWebtoon(User user) {
        int howManyWebtoons = 5;
        List<Webtoon> userGenreWebtoons = webtoonRepository.findUserGenreWebtoon(user);
        Collections.shuffle(userGenreWebtoons);
        List<Webtoon> countedUserGenreWebtoons = new ArrayList<>();
        for (int i = 0; i < howManyWebtoons; i++) {
            countedUserGenreWebtoons.add(userGenreWebtoons.get(i));
        }
        return countedUserGenreWebtoons.stream().map(WebtoonResponseDto::new).collect(Collectors.toList());
    }

    //비슷한 취향을 가진 유저가 높게 평가한 작품 추천
    public List<WebtoonAndGenreResponseDto> getSimilarUserWebtoon(User user) {
        return webtoonRepository.findSimilarUserWebtoon(user);
    }

    //MD 추천
    public WebtoonResponseDto getMdWebtoon() {
        Webtoon webtoon = webtoonRepository.findById(1L).orElseThrow(
                () -> new NullPointerException("해당 id의 웹툰이 없습니다.")
        );
        return new WebtoonResponseDto(webtoon);
    }

    //완결 웹툰 추천
    public List<WebtoonAndGenreResponseDto> getFinishedWebtoon() {
        int howManyWebtoons = 5;
        List<WebtoonAndGenreResponseDto> finishedWebtoons = webtoonRepository.findFinishedWebtoon();
        Collections.shuffle(finishedWebtoons);
        List<WebtoonAndGenreResponseDto> countedFinishedWebtoons = new ArrayList<>();
        for (int i = 0; i < howManyWebtoons; i++) {
            countedFinishedWebtoons.add(finishedWebtoons.get(i));
        }
        return countedFinishedWebtoons;
    }


    public LocalDateTime startDate() {
        LocalDateTime date = LocalDateTime.now().minusDays(1);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);
        LocalDateTime startDate = LocalDateTime.now();
        List<DayOfWeek> week = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY
                , DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        for (int i = 0; i <= 6; i++) {
            if (dayOfWeek == week.get(i)) {
                System.out.println("i = " + i);
                startDate = LocalDateTime.now().minusDays(i + 1);
            }
        }
        return startDate.withHour(0).withMinute(0).withSecond(0);
    }

    //이번달 웹투니버스 종합순위
    public List<MonthRankResponseDto> getMonthTotalRank() {
        return webtoonRepository.getTotalRank();
    }

    //웹투니버스 네이버 웹툰 Top10
    public List<PlatformRankResponseDto> getMonthNaverRank() {
        List<Webtoon> monthNaverRank = webtoonRepository.getNaverRank();
        return monthNaverRank
                .stream()
                .map(PlatformRankResponseDto::new)
                .collect(Collectors.toList());
    }

    //웹투니버스 카카오 웹툰 Top10
    public List<PlatformRankResponseDto> getMonthKakaoRank() {
        List<Webtoon> monthKakaoRank = webtoonRepository.getKakaoRank();
        return monthKakaoRank
                .stream()
                .map(PlatformRankResponseDto::new)
                .collect(Collectors.toList());
    }


    /**
     * 웹툰의 기본정보들과 리뷰 리스트를 재공하는 구현체입니다.
     *
     * @param id 웹툰 id
     * @return WebtoonDetailDto
     */
    public WebtoonDetailDto getDetailAndReviewList(Long id,User user) {
        //해당 웹툰 정보 찾기
        Webtoon webtoon = webtoonRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id는 존재하지 않습니다.")
        );

        //해당 웹툰의 장르 찾기

        List<Genre> webtoonGenre = webtoonRepository.findWebToonGenre(webtoon);
        List<String> genreList = new ArrayList<>();
        for (Genre genre : webtoonGenre){
            genreList.add(genre.getGenreType());
        }

        //해당 웹툰의 리뷰 찾기
        List<Review> reviewList = reviewRepository.findReviewByWebToonId(id);

        //리뷰 Dto
        List<WebtoonDetailReviewResponseDto> ReviewDtoList = reviewList.stream()
                .map(WebtoonDetailReviewResponseDto::new)
                .collect(Collectors.toList());

        //해당 user가 좋아요한 리뷰 게시글의 id 리스트
        List<Long> reviewIdListByUser = reviewLikeRepository.findReviewIdListByUser(user.getId());

        //내가 찜한 웹툰인지 아닌지
        boolean exists = myListRepository.existsById(user.getId(), id);

        return new WebtoonDetailDto(reviewIdListByUser,exists,webtoon, genreList, ReviewDtoList);
    }

    /**
     * 비슷한 장르의 웹툰을 추천하는 기능을 제공하는 구현체입니다.
     *
     * @param id 웹툰 id
     * @return List<SimilarGenreToonDto> 웹툰 리스트
     */
    public List<SimilarGenreToonDto> getSimilarGenre(Long id) {

        Webtoon webtoon = webtoonRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id의 웹툰은 존재하지 않습니다.")
        );

        List<Genre> genre = webtoonRepository.findWebToonGenre(webtoon);


        List<Webtoon> webtoonList= new ArrayList<>();
        for (Genre g : genre) {
            //비슷한 장르의 웹툰 찾기
            List<Webtoon> similarWebtoonByGenre = webtoonRepository.findSimilarWebtoonByGenre(g.getGenreType(), webtoon);
            webtoonList.addAll(similarWebtoonByGenre);
        }

        return webtoonList.stream()
                .map(SimilarGenreToonDto::new)
                .collect(Collectors.toList());
    }

    public List<WebtoonResponseDto> getMyListWebtoons(User user) {
        List<Webtoon> myListWebtoon = webtoonRepository.findMyListWebtoon(user);
        return myListWebtoon.stream()
                .map(WebtoonResponseDto::new)
                .collect(Collectors.toList());
    }

//    @Cacheable(key = "#id", value = "getFirstId")
//    public String getFirstId(Long id) {
//        return webtoonRepository.findById(id).get().getToonTitle();
//    }

    public List<WebtoonResponseDto> getUnreviewdList() {
        List<Webtoon> Webtoons = webtoonRepository.findByReviewCountLessThanEqual(1);
        List<WebtoonResponseDto> UnreviewedWebtoons = Webtoons.stream()
                .map(WebtoonResponseDto::new)
                .collect(Collectors.toList());
        return UnreviewedWebtoons;
    }

    public List<WebtoonResponseDto> getSearchedWebtoon(String keyword) {
        List<Webtoon> webtoons = webtoonRepository.findSearchedWebtoon(keyword.substring(0,1));
        String trimKeyword = keyword.replace(" ", "");
        List<WebtoonResponseDto> webtoonResponseDtos = new ArrayList<>();
        for (Webtoon webtoon : webtoons) {
            if (webtoon.getToonTitle().replace(" ", "").contains(trimKeyword)) {
                webtoonResponseDtos.add(new WebtoonResponseDto(webtoon));
            }
        }
        return webtoonResponseDtos;
    }
}
