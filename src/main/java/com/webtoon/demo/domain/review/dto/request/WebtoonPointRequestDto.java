package com.webtoon.demo.domain.review.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WebtoonPointRequestDto {

    private Long webtoonId;
    private float userPointNumber;

    public WebtoonPointRequestDto(Long webtoonId, float userPointNumber) {
        this.webtoonId = webtoonId;
        this.userPointNumber = userPointNumber;
    }
}
