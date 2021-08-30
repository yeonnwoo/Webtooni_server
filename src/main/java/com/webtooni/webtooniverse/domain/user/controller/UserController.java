package com.webtooni.webtooniverse.domain.user.controller;


import com.webtooni.webtooniverse.domain.review.dto.response.ReviewWebtoonGenre;
import com.webtooni.webtooniverse.domain.review.service.ReviewService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.dto.request.UserInfoRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.request.UserOnBoardingRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
import com.webtooni.webtooniverse.domain.user.dto.response.UserInfoResponseDto;
import com.webtooni.webtooniverse.domain.user.dto.response.UserWebtoonAndReviewResponseDto;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.user.service.UserService;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import java.util.List;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.webtooni.webtooniverse.global.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final WebtoonService webtoonService;


    /**
     * 카카오 소셜 로그인 코드를 인증합니다.
     * @param code 카카오 인증 코드
     * @return jwt token
     */
    @GetMapping("user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) {
        return userService.kakaoLogin(code);
    }

    /**
     * 네이버 소셜 로그인 코드를 인증합니다.
     * @param code 네이버 인증 코드
     * @return jwt token
     */
    @GetMapping("user/naver/callback")
    public String naverLogin(@RequestParam String code) {
        return userService.naverLogin(code);
    }

    /**
     * 유저 초기 정보를 등록합니다.
     * @param userDetails 로그인한 유저 정보
     * @param requestDto 유저 선호 장르, 유저 이름, 유저 이미지를 담은 dto
     */
    @PostMapping("user/onBoarding")
    public void pick(@AuthenticationPrincipal UserDetailsImpl userDetails,
                     @Valid @RequestBody UserOnBoardingRequestDto requestDto) {
        if (userDetails == null) {
            throw new ApiRequestException("유저 정보를 찾을 수 없습니다.");
        }
        User user = userDetails.getUser();
        userService.pickGenre(user, requestDto);
    }

    /**
     * 베스트 리뷰어를 조회합니다.(리뷰 작성 개수 많은 유저)
     * @return 베스트 리뷰어 내용을 담은 dto list
     */
    @GetMapping("rank/reviewers")
    public List<BestReviewerResponseDto> getBestReviewers() {
        return userService.getBestReviewerRank();
    }

    /**
     * 로그인한 유저 정보를 조회합니다.
     * @param userDetails 로그인한 유저 정보
     * @return 로그인한 유저 정보를 담은 dto
     */
    @GetMapping("user/info")
    public UserInfoResponseDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다.");
        }
        return userService.getUserInfo(userDetails.getUser().getId());
    }

    /**
     * 마이페이지 url을 공유합니다.
     * @param user 검색할 user 이름
     * @return user가 좋아요한 리뷰 목록, 구독한 웹툰 정보, 유저 정보가 담긴 dto
     */
    @GetMapping("user/infos")
    public UserWebtoonAndReviewResponseDto getUserInfo(@PathParam("user") String user) {
        UserInfoResponseDto userInfoByUserName = userService.getUserInfoByUserName(user);
        List<WebtoonAndGenreResponseDto> myListWebtoons = webtoonService.getMyListWebtoons(user);
        List<ReviewWebtoonGenre> myReviews = reviewService.getMyReviewsAndGenre(user);
        return new UserWebtoonAndReviewResponseDto(myListWebtoons, myReviews, userInfoByUserName);
    }

    /**
     * 유저 정보를 수정합니다.
     * @param userDetails 로그인한 유저 정보
     * @param userInfoRequestDto 변경할 유저 이미지와 유저 이름이 담긴 dto
     */
    @PutMapping("user/info")
    public void updateUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @Valid @RequestBody UserInfoRequestDto userInfoRequestDto) {
        if (userDetails == null) {
            throw new ApiRequestException("유저 정보를 찾을 수 없습니다.");
        }
        userService.updateInfo(userDetails.getUser().getId(), userInfoRequestDto);
    }
}
