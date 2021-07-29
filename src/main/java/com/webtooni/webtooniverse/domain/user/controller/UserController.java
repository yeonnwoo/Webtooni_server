package com.webtooni.webtooniverse.domain.user.controller;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.user.dto.LoginRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.SignupRequestDto;
import com.webtooni.webtooniverse.domain.user.security.JwtTokenProvider;
import com.webtooni.webtooniverse.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/*")
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(user.getUserName(), user.getUserImg(), user.getUserGrade());
    }


}
