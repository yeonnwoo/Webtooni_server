package com.webtooni.webtooniverse.domain.review.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewLikeResponseDto {

    private List<ReviewLikeListResponseDto> likeListDto;
    private List<ReviewNewResponseDto> reviewDto;


    public ReviewLikeResponseDto(List<ReviewLikeListResponseDto> likeListDto, List<ReviewNewResponseDto> reviewDto) {
        this.likeListDto = likeListDto;
        this.reviewDto = reviewDto;
    }
}
