package com.sparta.webtooniverse.controller;

import com.sparta.webtooniverse.domain.User;
import com.sparta.webtooniverse.dto.LoginRequestDto;
import com.sparta.webtooniverse.dto.SignupRequestDto;
import com.sparta.webtooniverse.repository.UserRepository;
import com.sparta.webtooniverse.security.JwtTokenProvider;
import com.sparta.webtooniverse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/api/user/signup")
    public void registerUser(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }

    @PostMapping("/api/user/login")
    public String login(@RequestBody LoginRequestDto requestDto) {
        User user = userRepository.findByUserName(requestDto.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(user.getUserName(), user.getUserImg(), user.getUserGrade());
    }


}
