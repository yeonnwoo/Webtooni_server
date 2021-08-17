package com.webtooni.webtooniverse.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewStarResponseDto {

  private Long reviewId;

  private float toonAvgPoint;

  public ReviewStarResponseDto(Long reviewId, float toonAvgPoint) {
    this.reviewId = reviewId;
    this.toonAvgPoint = toonAvgPoint;
  }
}
