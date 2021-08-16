package com.webtooni.webtooniverse.domain.webtoon.controller;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.*;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class WebtoonController {

    private final WebtoonService webtoonService;

    @GetMapping("offer/best-reviewer")
    public BestReviewerWebtoonResponseDto getBestReviewerWebtoons() {
        return webtoonService.getBestReviewerWebtoon();
    }

    @GetMapping("offer/for-user")
    public List<WebtoonResponseDto> getForUserWebtoons(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return webtoonService.getForUserWebtoon(user);
    }

    @GetMapping("offer/similar-user")
    public List<WebtoonAndGenreResponseDto> getSimilarUserWebtoons(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return webtoonService.getSimilarUserWebtoon(user);
    }

    @GetMapping("offer/md")
    public WebtoonResponseDto getMdWebtoons() {
        return webtoonService.getMdWebtoon();
    }

    @GetMapping("offer/end")
    public List<WebtoonAndGenreResponseDto> getFinishedWebtoons() {
        return webtoonService.getFinishedWebtoon();
    }

    //이번달 웹투니버스 순위
    @GetMapping("rank/total")
    public List<WebtoonAndGenreResponseDto> getMonthTotalRanks() {
        return webtoonService.getMonthTotalRank();
    }

    //네이버 웹툰 Top10
    @GetMapping("rank/naver")
    public List<PlatformRankResponseDto> getMonthNaverRanks() {
        return webtoonService.getMonthNaverRank();
    }

    //카카오 웹툰 Top10
    @GetMapping("rank/kakao")
    public List<PlatformRankResponseDto> getMonthKakaoRanks() {
        return webtoonService.getMonthKakaoRank();
    }

    //웹툰,리뷰 상세 정보
    @GetMapping("webtoon/{id}")
    public WebtoonDetailDto getWebtoonDetail(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return webtoonService.getDetailAndReviewList(id,userDetails);
    }

    //비슷한 장르 추천
    @GetMapping("webtoon/{id}/offer/genre")
    public List<SimilarGenreToonDto> getSimilarWebtoon(@PathVariable Long id) {
        return webtoonService.getSimilarGenre(id);
    }

    @GetMapping("user/me/subscribe")
    public List<WebtoonResponseDto> getMyListWebtoons(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) { throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다."); }
        return webtoonService.getMyListWebtoons(userDetails.getUser().getId());
    }

    @GetMapping("reviews/suggestion")
    public List<WebtoonResponseDto> getUnreviewdlist() {
        return webtoonService.getUnreviewdList();
    }

    @GetMapping("search")
    public List<WebtoonAndGenreResponseDto> getSearchedWebtoon(@PathParam("keyword") String keyword) {
        return webtoonService.getSearchedWebtoon(keyword);
    }

}
