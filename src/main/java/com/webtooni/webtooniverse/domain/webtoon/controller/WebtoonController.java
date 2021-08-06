package com.webtooni.webtooniverse.domain.webtoon.controller;


import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.MonthRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import com.webtooni.webtooniverse.domain.webtoon.dto.response.SimilarGenreToonDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class WebtoonController {

    private final WebtoonService webtoonService;


    //이번달 웹투니버스 순위
    @GetMapping("rank/total")
    public List<MonthRankResponseDto> getMonthTotalRanks(){
        return webtoonService.getMonthTotalRank();
    }

    //네이버 웹툰 Top10
    @GetMapping("rank/naver")
    public List<PlatformRankResponseDto> getMonthNaverRanks(){
        return webtoonService.getMonthNaverRank();
    }

    //카카오 웹툰 Top10
    @GetMapping("rank/kakao")
    public List<PlatformRankResponseDto> getMonthKakaoRanks(){
        return webtoonService.getMonthKakaoRank();
    }


    @GetMapping("webtoon/{id}")
    public WebtoonDetailDto getWebtoonDetail(@PathVariable Long id)
    {
        return webtoonService.getDetailAndReviewList(id);
    }

    @GetMapping("webtoon/{id}/offer/genre")
    public List<SimilarGenreToonDto> getSimilarWebtoon(@PathVariable Long id)
    {
        return webtoonService.getSimilarGenre(id);
    }


}
