package com.webtooni.webtooniverse.domain.review.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WebtoonPointRequestDto {

    private Long webtoonId;

    private float userPointNumber;
}
