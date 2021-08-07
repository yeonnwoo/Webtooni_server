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
    private LocalDateTime createDate;
    private String toonTitle;


    public ReviewNewResponseDto(Review review) {
        this.reviewContent = review.getReviewContent();
        this.createDate = review.getCreateDate();
    }

}
