package com.webtooni.webtooniverse.domain.review.dto.response;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;

@Getter
public class ReviewBestResponseDto {

    private Long id;
    private int userImg;
    private String userName;
    private float userPointNumber;
    private String reviewContent;
    private String toonTitle;

    public ReviewBestResponseDto(User user, Review review, Webtoon webtoon) {
        this.id = user.getId();
        this.userImg = user.getUserImg();
        this.userName = user.getUserName();
        this.userPointNumber = review.getUserPointNumber();
        this.reviewContent = review.getReviewContent();
        this.toonTitle = webtoon.getToonTitle();
    }

}