package com.webtooni.webtooniverse.domain.user.controller;


import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGenre;
import com.webtooni.webtooniverse.domain.user.dto.UserGenreRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.UserInfoRequestDto;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

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

    @PostMapping("/api/v1/user/genre")
    public List<UserGenre> pick(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserGenreRequestDto requestDto){
        User user = userDetails.getUser();
        List<UserGenre> userGenres = userService.pickGenre(user, requestDto);
        return userGenres;
    }
}
