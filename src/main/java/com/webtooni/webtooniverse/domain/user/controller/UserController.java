package com.webtooni.webtooniverse.domain.user.controller;


import com.webtooni.webtooniverse.domain.review.dto.response.MyReviewResponseDto;
import com.webtooni.webtooniverse.domain.review.service.ReviewService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.user.dto.request.UserInfoRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.request.UserOnBoardingRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
import com.webtooni.webtooniverse.domain.user.dto.response.UserInfoResponseDto;
import com.webtooni.webtooniverse.domain.user.dto.response.UserWebtoonAndReviewResponseDto;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.user.service.UserService;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import java.util.List;
import java.util.Optional;
import javax.websocket.server.PathParam;
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
    private final UserRepository userRepository;

    @GetMapping("user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) {
        return userService.kakaoLogin(code);
    }

    @GetMapping("user/naver/callback")
    public String naverLogin(@RequestParam String code) {
        return userService.naverLogin(code);
    }

    @PutMapping("user/info/{id}")
    public void update(@PathVariable Long id, @RequestBody UserInfoRequestDto requestDto) {
        userService.updateInfo(id, requestDto);
    }

    @PostMapping("user/onBoarding")
    public void pick(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody UserOnBoardingRequestDto requestDto) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다.");
        }
        User user = userDetails.getUser();
        userService.pickGenre(user, requestDto);
    }

    //베스트 리뷰어(리뷰개수많은순서)
    @GetMapping("rank/reviewers")
    public List<BestReviewerResponseDto> getBestReviewers() {
        return userService.getBestReviewerRank();
    }

    @GetMapping("user/info")
    public UserInfoResponseDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다.");
        }
        return userService.getUserInfo(userDetails.getUser().getId());
    }

    @GetMapping("user/infos")
    public UserWebtoonAndReviewResponseDto getUserInfo(@PathParam("user") String user) {
        User thisUser=webtoonService.getUser(user);
        List<WebtoonResponseDto> myListWebtoons = webtoonService.getMyListWebtoons(user);
        List<MyReviewResponseDto> myReviews = reviewService.getMyReviews(user);
        return new UserWebtoonAndReviewResponseDto(myListWebtoons, myReviews,thisUser);
    }

    @PutMapping("user/info")
    public void updateUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody UserInfoRequestDto userInfoRequestDto) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다.");
        }
        userService.updateInfo(userDetails.getUser().getId(), userInfoRequestDto);
    }
}
