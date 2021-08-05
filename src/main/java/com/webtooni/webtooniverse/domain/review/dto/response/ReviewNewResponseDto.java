package com.webtooni.webtooniverse.domain.review.dto.response;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewNewResponseDto {

    private Long id;
    private int userImg;
    private String userName;
    private float userPointNumber;
    private String reviewContent;
    private String toonTitle;
    private LocalDateTime createDate;


    public ReviewNewResponseDto(Review review) {
        this.reviewContent = review.getReviewContent();
        this.createDate = review.getCreateDate();
    }

    public ReviewNewResponseDto(Review review, Long id, int userImg, String userName, float userPointNumber,
                                String toonTitle) {
        this.id = id;
        this.userImg = userImg;
        this.userName = userName;
        this.userPointNumber = userPointNumber;
        this.reviewContent = review.getReviewContent();
        this.toonTitle = toonTitle;
        this.createDate = review.getCreateDate();
    }
}
