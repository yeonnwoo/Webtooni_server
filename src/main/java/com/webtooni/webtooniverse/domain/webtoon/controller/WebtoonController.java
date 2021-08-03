package com.webtooni.webtooniverse.domain.webtoon.controller;

import com.webtooni.webtooniverse.domain.webtoon.dto.response.MonthRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class WebtoonController {

    private final WebtoonService webtoonService;

    //이번달 웹투니버스 순위
    @GetMapping("/api/v1/rank/total")
    public List<MonthRankResponseDto> getMonthRank(){
        return webtoonService.getMonthRank();
    }

}
