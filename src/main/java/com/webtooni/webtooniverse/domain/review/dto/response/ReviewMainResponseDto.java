package com.webtooni.webtooniverse.domain.review.dto.response;

import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;

@Getter
public class ReviewMainResponseDto {

    private Long id;
    private int userImg;
    private String userName;
    private float userPointNumber;
    private String reviewContent;
    private String toonTitle;

    public ReviewMainResponseDto(User user, float userPointNumber, String reviewContent, String toonTitle) {
        this.id = user.getId();
        this.userImg = user.getUserImg();
        this.userName = user.getUserName();
        this.userPointNumber = userPointNumber;
        this.reviewContent = reviewContent;
        this.toonTitle = toonTitle;
    }
}
