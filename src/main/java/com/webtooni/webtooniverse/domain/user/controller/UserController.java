package com.webtooni.webtooniverse.domain.user.controller;


import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
import com.webtooni.webtooniverse.domain.user.dto.response.UserInfoResponseDto;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGenre;
import com.webtooni.webtooniverse.domain.user.dto.UserGenreRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.UserInfoRequestDto;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private final UserService userService;

    @GetMapping("user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) {
        // authorizedCode:
        return userService.kakaoLogin(code);
    }

    @PostMapping("user/genre")
    public List<UserGenre> pick(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserGenreRequestDto requestDto) {
        User user = userDetails.getUser();
        return userService.pickGenre(user, requestDto);
    }

    //베스트 리뷰어(리뷰개수많은순서)
    @GetMapping("rank/reviewers")
    public List<BestReviewerResponseDto> getBestReviewers() {
        return userService.getBestReviewerRank();
    }

    @GetMapping("user/info")
    public UserInfoResponseDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.getUserInfo(userDetails.getUser());
    }

    @PutMapping("user/info")
    public void updateUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestBody UserInfoRequestDto userInfoRequestDto) {
        userService.updateInfo(userDetails.getUser().getId(), userInfoRequestDto );
    }
}
