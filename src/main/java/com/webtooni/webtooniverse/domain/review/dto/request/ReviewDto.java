package com.webtooni.webtooniverse.domain.review.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewDto {

    private String reviewContent;

    public ReviewDto(String reviewContent) {
        this.reviewContent = reviewContent;
    }
}
