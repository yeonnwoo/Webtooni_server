package com.webtooni.webtooniverse.domain.review.dto.response;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WebtoonDetailReviewResponseDto {

    private String userName;
    private int userImg;
    private Long reviewId;
    private int userGrade;
    private String reviewContent;
    private float userPointNumber;
    private int likeCount;
    private LocalDateTime createDate;


    public WebtoonDetailReviewResponseDto(Review review) {
        this.userName = review.getUser().getUserName();
        this.userImg = review.getUser().getUserImg();
        this.reviewId = review.getId();
        this.userGrade = review.getUser().getUserGrade();
        this.reviewContent = review.getReviewContent();
        this.userPointNumber = review.getUserPointNumber();
        this.likeCount = review.getLikeCount();
        this.createDate = review.getCreateDate();
    }


}
