package com.webtooni.webtooniverse.domain.review.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewLikeResponseDto {

    private List<Long> LikeReviewIdList;
    private List<ReviewResponseDto> reviews;
    private Long postCount;


    public ReviewLikeResponseDto(List<Long> LikeReviewIdList, List<ReviewResponseDto> reviews,
        Long postCount) {
        this.LikeReviewIdList = LikeReviewIdList;
        this.reviews = reviews;
        this.postCount = postCount;
    }
}

