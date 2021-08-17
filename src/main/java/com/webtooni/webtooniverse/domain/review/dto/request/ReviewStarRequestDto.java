package com.webtooni.webtooniverse.domain.review.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewStarRequestDto {

  private Long reviewId;


  public ReviewStarRequestDto(Long reviewId) {
    this.reviewId = reviewId;
  }
}
