package com.webtooni.webtooniverse.domain.webtoon.controller;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.BestReviewerWebtoonResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.RankTotalResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.SimilarGenreToonDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import java.util.List;
import java.util.Set;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class WebtoonController {

    private final WebtoonService webtoonService;

    /**
     * 베스트 리뷰어 추천 웹툰을 조회합니다.
     * @return 베스트 리뷰어와 베스트 리뷰어 추천 웹툰을 담은 dto
     */
    @GetMapping("offer/best-reviewer")
    public BestReviewerWebtoonResponseDto getBestReviewerWebtoons() {
        return webtoonService.getBestReviewerWebtoon();
    }

    /**
     * 유저 관심 장르에 대한 웹툰을 조회합니다.
     * @param userDetails 로그인한 유저 정보
     * @return 관심 장르가 일치하는 웹툰 내용을 담은 dto list
     */
    @GetMapping("offer/for-user")
    public List<WebtoonResponseDto> getForUserWebtoons(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다.");
        }
        User user = userDetails.getUser();
        return webtoonService.getForUserWebtoon(user);
    }

    /**
     * 유저와 비슷한 취향을 가진 사용자가 높게 평가한 웹툰을 조회합니다.
     * @param userDetails 로그인한 유저 정보
     * @return 비슷한 취향의 사용자가 추천하는 웹툰 내용을 담은 dto list
     */
    @GetMapping("offer/similar-user")
    public List<WebtoonAndGenreResponseDto> getSimilarUserWebtoons(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userDetails.getUser();
        return webtoonService.getSimilarUserWebtoon(user);
    }

    /**
     * md 추천 웹툰을 조회합니다.
     * @return 웹툰 내용을 담은 dto
     */
    @GetMapping("offer/md")
    public WebtoonResponseDto getMdWebtoons() {
        return webtoonService.getMdWebtoon();
    }

    /**
     * 완결 추천 웹툰을 조회합니다.
     * @return 완결된 추천 웹툰 내용을 담은 dto list
     */
    @GetMapping("offer/end")
    public List<WebtoonAndGenreResponseDto> getFinishedWebtoons() {
        return webtoonService.getFinishedWebtoon();
    }

    /**
     * 이번주 웹투니버스 순위를 조회합니다.
     * @return 상위 랭크된 웹툰 목록
     */
    @GetMapping("rank/total")
    public Set<RankTotalResponseDto> getMonthTotalRanks() {
        return webtoonService.getWeeklyTotalRank();
    }

    /**
     * 네이버 평점순 웹툰을 조회합니다.
     * @return 웹툰 내용을 담은 dto list
     */
    @GetMapping("rank/naver")
    public List<PlatformRankResponseDto> getMonthNaverRanks() {
        return webtoonService.getNaverRank();
    }

    /**
     * 카카오 평점순 웹툰을 조회합니다.
     * @return 웹툰 내용을 담은 dto list
     */
    @GetMapping("rank/kakao")
    public List<PlatformRankResponseDto> getMonthKakaoRanks() {
        return webtoonService.getKakaoRank();
    }

    /**
     * 웹툰 상세 내용을 조회합니다.
     * @param id 웹툰 id
     * @param userDetails 로그인한 유저 정보
     * @return 웹툰 내용과 리뷰 목록을 포함한 dto
     */
    @GetMapping("webtoon/{id}")
    public WebtoonDetailDto getWebtoonDetail(@PathVariable Long id
        , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return webtoonService.getDetailAndReviewList(id, userDetails);
    }

    /**
     * 해당 웹툰과 비슷한 장르의 웹툰을 추천합니다.
     * @param id 웹툰 id
     * @return 비슷한 장르 웹툰 내용을 담은 dto list
     */
    @GetMapping("webtoon/{id}/offer/genre")
    public List<SimilarGenreToonDto> getSimilarWebtoon(@PathVariable Long id) {
        return webtoonService.getSimilarGenre(id);
    }

    /**
     * 마이리스트에 담은 웹툰 목록을 조회합니다.
     * @param userDetails 로그인한 유저 정보
     * @return 마이리스트 웹툰 내용을 담은 dto 목록
     */
    @GetMapping("user/me/subscribe")
    public List<WebtoonAndGenreResponseDto> getMyListWebtoons(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다.");
        }
        return webtoonService.getMyListWebtoons(userDetails.getUser().getUserName());
    }

    /**
     * 리뷰 개수가 0개인 웹툰 목록을 조회합니다.
     * @return 웹툰 내용을 담은 dto list
     */
    @GetMapping("reviews/suggestion")
    public List<WebtoonResponseDto> getUnreviewdlist() {
        return webtoonService.getUnreviewdList();
    }

    /**
     * 웹툰을 검색합니다.
     * @param keyword 검색 내용
     * @return 웹툰 내용을 담은 dto list
     */
    @GetMapping("search")
    public List<WebtoonAndGenreResponseDto> getSearchedWebtoon(
        @PathParam("keyword") String keyword) {
        return webtoonService.getSearchedWebtoon(keyword);
    }

}
