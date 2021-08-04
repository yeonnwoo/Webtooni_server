package com.webtooni.webtooniverse.domain.review.controller;

import com.webtooni.webtooniverse.domain.review.dto.response.ReviewMainResponseDto;
import com.webtooni.webtooniverse.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    //메인페이지에 리뷰(최신순/ 베스트순) 불러오기
    @GetMapping("/api/v1/rank/reviews")
    public ReviewMainResponseDto getTotalReviews(){
        return reviewService.getMainReview();
    }
}
