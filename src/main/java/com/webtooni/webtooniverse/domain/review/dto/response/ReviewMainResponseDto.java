package com.webtooni.webtooniverse.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewMainResponseDto {

    private List<ReviewResponseDto> bestReview;
    private List<ReviewResponseDto> newReview;

    public ReviewMainResponseDto(List<ReviewResponseDto> reviewBestResponseDto,
                                 List<ReviewResponseDto> reviewResponseDto){

        this.bestReview = reviewBestResponseDto;
        this.newReview = reviewResponseDto;
    }
}
