package com.webtooni.webtooniverse.domain.user.controller;


import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;


import org.springframework.web.bind.annotation.*;


import java.util.List;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.user.dto.request.LoginRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.request.SignupRequestDto;
import com.webtooni.webtooniverse.domain.user.security.JwtTokenProvider;
import com.webtooni.webtooniverse.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class UserController {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    //베스트 리뷰어(리뷰개수많은순서)
    @GetMapping("/api/v1/rank/reviewers")
    public List<BestReviewerResponseDto> getBestReviewers() {
        return userService.getBestReviewerRank();
    }

    @PostMapping("/api/v1/user/register")
    public void registerUser(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }

    @PostMapping("/api/v1/user/login")
    public String login(@RequestBody LoginRequestDto requestDto) {
        User user = userRepository.findByUserName(requestDto.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(user.getUserName(), user.getUserImg(), user.getUserGrade());

    }
}
