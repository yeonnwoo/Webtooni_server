package com.webtooni.webtooniverse.domain.review.controller;

import com.webtooni.webtooniverse.domain.review.dto.request.WebtoonPointRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewContentRequestDto;
import com.webtooni.webtooniverse.domain.review.service.ReviewService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 작성(수정)
    @PutMapping("/api/v1/reviews/{id}")
    public Long updateReview(@PathVariable Long id, @RequestBody ReviewContentRequestDto reviewDto) {
        return reviewService.updateReview(id, reviewDto);
    }

    //리뷰 삭제
    @DeleteMapping("/api/v1/reviews/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }

    //리뷰에 좋아요
    @PostMapping("/api/v1/reviews/{id}/like")
    public void clickReviewLike(@PathVariable Long id) {
        /**
         * 로그인된 유저 정보로 변경 되어야함
         */
        User user = User.builder()
                .userImg(1)
                .build();

        reviewService.clickReviewLike(id, user);
    }


    /**
     * 웹툰에 별점주기
     *
     * @param - reviewStarDto : 웹툰 id,userPointNumber
     */
    @PutMapping("/api/v1/reviews/star")
    public void updateStar(@RequestBody WebtoonPointRequestDto reviewStarDto) {
        /**
         * 로그인된 유저 정보로 변경 되어야함
         */
        User user = User.builder()
                .userImg(1)
                .build();

        reviewService.clickWebtoonPointNumber(reviewStarDto, user);
    }
}
