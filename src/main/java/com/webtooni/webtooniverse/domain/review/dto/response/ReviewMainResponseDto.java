package com.webtooni.webtooniverse.domain.review.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewMainResponseDto {

    private List<ReviewResponseDto> bestReview;
    private List<ReviewResponseDto> newReview;

    public ReviewMainResponseDto(List<ReviewResponseDto> reviewBestResponseDto,
        List<ReviewResponseDto> reviewResponseDto) {

        this.bestReview = reviewBestResponseDto;
        this.newReview = reviewResponseDto;
    }
}
