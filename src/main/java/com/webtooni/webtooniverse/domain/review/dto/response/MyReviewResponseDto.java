package com.webtooni.webtooniverse.domain.review.dto.response;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyReviewResponseDto {

    private Long reviewId;
    private String reviewContent;
    private float userPointNumber;
    private int likeCount;
    private Webtoon webtoon;
    private LocalDateTime createTime;

    public MyReviewResponseDto(Review review) {
        this.reviewId = review.getId();
        this.reviewContent = review.getReviewContent();
        this.userPointNumber = review.getUserPointNumber();
        this.likeCount = review.getLikeCount();
        this.webtoon = review.getWebtoon();
        this.createTime = review.getCreateDate();
    }
}
