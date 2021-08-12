package com.webtooni.webtooniverse.domain.review.dto.response;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewLikeListResponseDto {

    private Review reviewId;

    public ReviewLikeListResponseDto(ReviewLike reviewLike){
        this.reviewId = reviewLike.getReview();
    }
}
