package com.webtooni.webtooniverse.domain.webtoon.controller;

import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class WebtoonController {

    private final WebtoonService webtoonService;


    @GetMapping("offer/best-reviewer")
    public List<WebtoonResponseDto> getBestReviewerWebtoons(){
        return webtoonService.getBestReviewerWebtoon();
    }

//    @GetMapping("offer/for-user")
//    public List<WebtoonResponseDto> getForUserWebtoons(){
//        return webtoonService.getForUserWebtoon(user);
//    }
//
//    @GetMapping("offer/similar-user")
//    public List<WebtoonResponseDto> getSimilarUserWebtoons(){
//        return webtoonService.getSimilarUserWebtoon(user);
//    }

    @GetMapping("offer/md")
    public WebtoonResponseDto getMdWebtoons(){
        return webtoonService.getMdWebtoon();
    }

    @GetMapping("offer/end")
    public List<WebtoonResponseDto> getFinishedWebtoons(){
        return webtoonService.getFinishedWebtoon();
    }

}
