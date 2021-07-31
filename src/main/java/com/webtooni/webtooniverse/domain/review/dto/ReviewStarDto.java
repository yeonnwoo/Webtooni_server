package com.webtooni.webtooniverse.domain.review.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewStarDto {

    private Long webtoonId;

    private float userPointNumber;
}
