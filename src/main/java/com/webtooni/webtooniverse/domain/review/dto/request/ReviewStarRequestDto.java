package com.webtooni.webtooniverse.domain.review.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewStarRequestDto {

    private Long reviewId;

    private float toonAvgPoint;

    public ReviewStarRequestDto(Long reviewId, float toonAvgPoint) {
        this.reviewId = reviewId;
        this.toonAvgPoint = toonAvgPoint;
    }
}
