package com.webtooni.webtooniverse.domain.review.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
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
    private UserGrade userGrade;
    private String toonImg;
    private String toonAuthor;
    private String toonPlatform;
    private String toonWeekday;
    private boolean finished;


//    public ReviewNewResponseDto(Review review) {
//        this.reviewContent = review.getReviewContent();
//        this.createDate = review.getCreateDate();
//    }

    @QueryProjection
    public ReviewNewResponseDto(Long id, int userImg, String userName, float userPointNumber,
                                String reviewContent, String toonTitle, UserGrade userGrade, String toonImg,
                                String toonAuthor, String toonPlatform, String toonWeekday, boolean finished) {
        this.id = id;
        this.userImg = userImg;
        this.userName = userName;
        this.userPointNumber = userPointNumber;
        this.reviewContent = reviewContent;
        this.toonTitle = toonTitle;
        this.userGrade = userGrade;
        this.toonImg = toonImg;
        this.toonAuthor = toonAuthor;
        this.toonPlatform = toonPlatform;
        this.toonWeekday = toonWeekday;
        this.finished = finished;
    }
}
