package com.webtooni.webtooniverse.domain.review.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WebtoonPointRequestDto {

  private Long toonId;
  private float userPointNumber;

  public WebtoonPointRequestDto(Long toonId, float userPointNumber) {
    this.toonId = toonId;
    this.userPointNumber = userPointNumber;
  }
}
