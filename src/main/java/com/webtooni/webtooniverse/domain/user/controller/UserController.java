package com.webtooni.webtooniverse.domain.user.controller;


import com.webtooni.webtooniverse.domain.user.dto.UserInfoRequestDto;
import com.webtooni.webtooniverse.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {
//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
//    private final UserRepository userRepository;
//
//    @PostMapping("/api/v1/user/register")
//    public void registerUser(@Valid @RequestBody SignupRequestDto requestDto) {
//        userService.registerUser(requestDto);
//    }
//
//    @PostMapping("/api/v1/user/login")
//    public String login(@RequestBody LoginRequestDto requestDto) {
//        User user = userRepository.findByUserName(requestDto.getUserName())
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));
//        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }
//        return jwtTokenProvider.createToken(user.getUserName(), user.getUserImg(), user.getUserGrade());
//    }

    @GetMapping("/api/v1/user/kakao/callback")
    public String kakaoLogin(String code) {
        // authorizedCode:
        userService.kakaoLogin(code);

        return "redirect:/";
    }

    @PutMapping("/api/v1/user/info/{id}")
    public void update(@PathVariable Long id, @RequestBody UserInfoRequestDto requestDto){
        userService.updateInfo(id, requestDto);
    }
}
