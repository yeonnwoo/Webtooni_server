package com.webtooni.webtooniverse.domain.review.controller;

import com.webtooni.webtooniverse.domain.review.dto.ReviewStarDto;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewDto;
import com.webtooni.webtooniverse.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 수정
    @PutMapping("/api/v1/reviews/{id}")
    public Long updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto)
    {
        return reviewService.updateReview(id,reviewDto);
    }

    //리뷰 삭제
    @DeleteMapping("/api/v1/reviews/{id}")
    public void deleteReview(@PathVariable Long id)
    {
        reviewService.deleteReview(id);
    }

    //리뷰에 좋아요
    @PostMapping("/api/v1/reviews/{id}/like")
    public void clickReviewLike(@PathVariable Long id)
    {
        reviewService.clickReviewLike(id);
    }


    //웹툰에 별점 주기
    //별점 누름 -> 별점 정보 & 유저 정보 제외 모두 null값

    /**
     *
     * @param - reviewStarDto : 웹툰 id,userPointNumber
     */
    @PutMapping("/api/v1/reviews/star")
    public void updateStar(@RequestBody ReviewStarDto reviewStarDto)
    {
        reviewService.updateUserPointNumber(reviewStarDto);

    }
}
