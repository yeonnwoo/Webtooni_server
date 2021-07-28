package com.webtooni.webtooniverse.domain.webtoon.controller;

import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class WebtoonController {

    private final WebtoonService webtoonService;


    @GetMapping("offer/best-reviewer")
    public WebtoonResponseDto getBestReviewerWebtoons(){
        return webtoonService.getBestReviewerWebtoon();
    }

    @GetMapping("offer/for-user")
    public WebtoonResponseDto getForUserWebtoons(){
        return webtoonService.getForUserWebtoon();
    }

    @GetMapping("offer/similar-user")
    public WebtoonResponseDto getSimilarUserWebtoons(){
        return webtoonService.getSimilarUserWebtoon();
    }

    @GetMapping("offer/md")
    public WebtoonResponseDto getMdWebtoons(){
        return webtoonService.getMdWebtoon();
    }

    @GetMapping("offer/end")
    public WebtoonResponseDto getFinishedWebtoons(){
        return webtoonService.getFinishedWebtoon();
    }

}
