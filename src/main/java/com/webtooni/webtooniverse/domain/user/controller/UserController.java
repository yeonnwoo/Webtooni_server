package com.webtooni.webtooniverse.domain.user.controller;

import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
import com.webtooni.webtooniverse.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    //베스트 리뷰어(등급높은순)
    @GetMapping("/api/v1/rank/reviewers")
    public List<BestReviewerResponseDto> getBestReviewers(){
        return userService.getBestReviewer();
    }
}
