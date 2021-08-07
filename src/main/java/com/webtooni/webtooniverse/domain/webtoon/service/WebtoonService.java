package com.webtooni.webtooniverse.domain.webtoon.service;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.MonthRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.SimilarGenreToonDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
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

    //금주의 웹툰 평론가 추천
    public List<WebtoonResponseDto> getBestReviewerWebtoon() {
        List<Webtoon> bestReviewerWebtoons = webtoonRepository.findBestReviewerWebtoon(startDate());
        return bestReviewerWebtoons.stream().map(WebtoonResponseDto::new).collect(Collectors.toList());
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
    public List<WebtoonResponseDto> getSimilarUserWebtoon(User user) {
        List<Webtoon> similarUserWebtoons = webtoonRepository.findSimilarUserWebtoon(user);
        return similarUserWebtoons.stream().map(WebtoonResponseDto::new).collect(Collectors.toList());
    }

    //MD 추천
    public WebtoonResponseDto getMdWebtoon() {
        Webtoon webtoon = webtoonRepository.findById(1L).orElseThrow(
                () -> new NullPointerException("해당 id의 웹툰이 없습니다.")
        );
        return new WebtoonResponseDto(webtoon);
    }

    /**
     * TODO 완결웹툰 5개를 보여줘야하는데 매번 같은 걸 보여줄 것 같아서 상위 10개에서 5개 랜덤으로 돌리는 방식 채택중 (추후 수정 필요)
     */
    //완결 웹툰 추천
    public List<WebtoonResponseDto> getFinishedWebtoon() {
        int howManyWebtoons = 5;
        List<Webtoon> finishedWebtoons = webtoonRepository.findFinishedWebtoon();
        Collections.shuffle(finishedWebtoons);
        List<Webtoon> countedFinishedWebtoons = new ArrayList<>();
        for (int i = 0; i < howManyWebtoons; i++) {
            countedFinishedWebtoons.add(finishedWebtoons.get(i));
        }
        return countedFinishedWebtoons.stream().map(WebtoonResponseDto::new).collect(Collectors.toList());
    }


    public LocalDateTime startDate(){
        LocalDateTime date = LocalDateTime.now().minusDays(1);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);
        LocalDateTime startDate = LocalDateTime.now();
        List<DayOfWeek> week = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY
                ,DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        for (int i = 0; i <= 6; i++) {
            if (dayOfWeek == week.get(i)) {
                System.out.println("i = " + i);
                startDate = LocalDateTime.now().minusDays(i + 1);
            }
        }
        return startDate.withHour(0).withMinute(0).withSecond(0);}

    //이번달 웹투니버스 종합순위
    public List<MonthRankResponseDto> getMonthTotalRank(){
        List<Webtoon> monthTotalRank = webtoonRepository.getTotalRank();
        return monthTotalRank
                .stream()
                .map(MonthRankResponseDto::new)
                .collect(Collectors.toList());
    }

    //웹투니버스 네이버 웹툰 Top10
    public List<PlatformRankResponseDto> getMonthNaverRank(){
        List<Webtoon> monthNaverRank = webtoonRepository.getNaverRank();
        return monthNaverRank
                .stream()
                .map(PlatformRankResponseDto::new)
                .collect(Collectors.toList());
    }

    //웹투니버스 카카오 웹툰 Top10
    public List<PlatformRankResponseDto> getMonthKakaoRank(){
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
    public WebtoonDetailDto getDetailAndReviewList(Long id) {
        //해당 웹툰 정보 찾기
        Webtoon webtoon = webtoonRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id는 존재하지 않습니다.")
        );

        //해당 웹툰의 장르 찾기
        List<Genre> WebToonGenre = webtoonRepository.findWebToonGenre(webtoon);
        List<String> genreList = new ArrayList<>();
        genreList.add(WebToonGenre.get(0).getGenreType());
        genreList.add(WebToonGenre.get(1).getGenreType());

        //해당 웹툰의 리뷰 찾기
        List<Review> reviewList = reviewRepository.findReviewByWebToonId(id);

        return new WebtoonDetailDto(webtoon, genreList, reviewList);
    }

    /**
     * 비슷한 장르의 웹툰을 추천하는 기능을 제공하는 구현체입니다.
     *
     * @param id 웹툰 id
     * @return List<SimilarGenreToonDto> 웹툰 리스트
     */
    public List<SimilarGenreToonDto> getSimilarGenre(Long id) {

        //웹툰 찾기
        Webtoon webtoon = webtoonRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id의 웹툰은 존재하지 않습니다.")
        );

        List<Genre> genre = webtoonRepository.findWebToonGenre(webtoon);

        //비슷한 장르의 웹툰 찾기
        List<Webtoon> webtoonList = webtoonRepository.findSimilarWebtoonByGenre(genre.get(1).getGenreType(), webtoon);

        return webtoonList.stream()
                .map(SimilarGenreToonDto::new)
                .collect(Collectors.toList());
    }

}
