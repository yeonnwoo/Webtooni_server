package com.webtooni.webtooniverse.domain.webtoon.service;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;

import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.MonthRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

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


}
