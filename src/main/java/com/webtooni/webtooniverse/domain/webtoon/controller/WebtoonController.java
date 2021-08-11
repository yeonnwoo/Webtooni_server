package com.webtooni.webtooniverse.domain.webtoon.controller;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.*;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class    WebtoonController {

    private final WebtoonService webtoonService;

    @GetMapping("offer/best-reviewer")
    public BestReviewerWebtoonResponseDto getBestReviewerWebtoons(){
        return webtoonService.getBestReviewerWebtoon();
    }

    @GetMapping("offer/for-user")
    public List<WebtoonResponseDto> getForUserWebtoons(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return webtoonService.getForUserWebtoon(user);
    }

    @GetMapping("offer/similar-user")
    public List<WebtoonResponseDto> getSimilarUserWebtoons(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return webtoonService.getSimilarUserWebtoon(user);
    }

    @GetMapping("offer/md")
    public WebtoonResponseDto getMdWebtoons(){
        return webtoonService.getMdWebtoon();
    }

    @GetMapping("offer/end")
    public List<WebtoonResponseDto> getFinishedWebtoons(){
        return webtoonService.getFinishedWebtoon();
    }

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

    @GetMapping("user/me/subscribe")
    public List<WebtoonResponseDto> getMyListWebtoons(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return webtoonService.getMyListWebtoons(userDetails.getUser());
    }

    @GetMapping("test")
    public String test() {
        return webtoonService.getFirstId(1L);
    }

    @GetMapping("reviews/suggestion")
    public List<WebtoonResponseDto> getUnreviewdlist() {
        return webtoonService.getUnreviewdList();
    }

}
