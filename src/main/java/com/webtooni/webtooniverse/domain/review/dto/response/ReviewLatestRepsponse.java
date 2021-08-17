package com.webtooni.webtooniverse.domain.review.dto.response;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewLatestRepsponse {

    private String reviewContent;
    private float userPointNumber;
    private int likeCount;
    private Webtoon webtoon;
    private User user;
    private int userImg;
    private String userName;
    private UserGrade userGrade;

    public ReviewLatestRepsponse(Review review) {
        this.reviewContent = review.getReviewContent();
        this.userPointNumber = review.getUserPointNumber();
        this.likeCount = review.getLikeCount();
        this.webtoon = review.getWebtoon();
        this.user = review.getUser();
        this.userImg = review.getUser().getUserImg();
        this.userGrade = review.getUser().getUserGrade();
        this.userName = review.getUser().getUserName();
    }

}

