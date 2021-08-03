package com.webtooni.webtooniverse.domain.webtoon.service;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;

import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.MonthRankResponseDto;
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



}
