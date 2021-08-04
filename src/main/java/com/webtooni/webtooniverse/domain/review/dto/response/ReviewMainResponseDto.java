package com.webtooni.webtooniverse.domain.review.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ReviewMainResponseDto {

    private List<ReviewBestResponseDto> bestReview;
    private List<ReviewNewResponseDto> newReview;

    public ReviewMainResponseDto(List<ReviewBestResponseDto> reviewBestResponseDto,
                                 List<ReviewNewResponseDto> reviewNewResponseDto){

        this.bestReview = reviewBestResponseDto;
        this.newReview = reviewNewResponseDto;
    }
}
