package com.webtooni.webtooniverse.domain.review.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReviewStarRequestDto {

    private Long reviewId;

    public ReviewStarRequestDto(Long reviewId) {
        this.reviewId = reviewId;
    }
}
