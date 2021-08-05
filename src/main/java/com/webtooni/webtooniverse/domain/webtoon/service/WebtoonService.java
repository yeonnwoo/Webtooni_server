package com.webtooni.webtooniverse.domain.webtoon.service;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepositoryImpl;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    //금주의 웹툰 평론가 추천
    public List<WebtoonResponseDto> getBestReviewerWebtoon() {
        List<Webtoon> bestReviewerWebtoons = webtoonRepository.findBestReviewerWebtoon(startDate());
        return bestReviewerWebtoons.stream().map(WebtoonResponseDto::new).collect(Collectors.toList());
    }

    //유저 관심 장르 중 랜덤 추천
    public List<WebtoonResponseDto> getForUserWebtoon() {
//        int howManyWebtoons = 5;
//        List<Webtoon> userGenreWebtoons = webtoonRepository.findUserGenreWebtoon(user);
//        Collections.shuffle(userGenreWebtoons);
//        List<Webtoon> countedUserGenreWebtooons = new ArrayList<>();
//        for (int i = 0; i < howManyWebtoons; i++) {
//            countedUserGenreWebtooons.add(userGenreWebtoons.get(i));
//        }
//        return countedUserGenreWebtooons.stream().map(WebtoonResponseDto::new).collect(Collectors.toList());

        return getBestReviewerWebtoon();
    }

    //비슷한 취향을 가진 유저가 높게 평가한 작품 추천
    public List<WebtoonResponseDto> getSimilarUserWebtoon() {
//        List<Webtoon> similarUserWebtoons = webtoonRepository.findSimilarUserWebtoon(user);
//        return similarUserWebtoons.stream().map(WebtoonResponseDto::new).collect(Collectors.toList());
        return getBestReviewerWebtoon();
    }

    //MD 추천
    public WebtoonResponseDto getMdWebtoon() {
        Webtoon webtoon = webtoonRepository.findById(1L).orElseThrow(
                () -> new NullPointerException("해당 id의 웹툰이 없습니다.")
        );
        return new WebtoonResponseDto(webtoon);
    }

    //완결 웹툰 추천
    public List<WebtoonResponseDto> getFinishedWebtoon() {
        int howManyWebtoons = 5;
        List<Webtoon> finishedWebtoons = webtoonRepository.findFinishedWebtoon();
        Collections.shuffle(finishedWebtoons);
        List<Webtoon> countedFinishedWebtoons = new ArrayList<>();
        for (int i = 0; i < howManyWebtoons; i++) {
            countedFinishedWebtoons.add(finishedWebtoons.get(i));
        }
        return finishedWebtoons.stream().map(WebtoonResponseDto::new).collect(Collectors.toList());
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
        return startDate.withHour(0).withMinute(0).withSecond(0);
    }

}
