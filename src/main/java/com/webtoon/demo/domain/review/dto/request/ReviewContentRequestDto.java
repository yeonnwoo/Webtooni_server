package com.webtoon.demo.domain.review.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewContentRequestDto {

    private String reviewContent;

    public ReviewContentRequestDto(String reviewContent) {
        this.reviewContent = reviewContent;
    }
}
