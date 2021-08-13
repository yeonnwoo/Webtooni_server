package com.webtooni.webtooniverse.domain.review.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewBestResponseDto {

    private Long userId;
    private int userImg;
    private String userName;
    private float userPointNumber;
    private String reviewContent;
    private int likeCount;
    private LocalDateTime createDate;
    private Long toonId;
    private String toonTitle;
    private String toonImg;
    private String toonAuthor;
    private String toonPlatform;
    private String toonWeekday;
    private boolean finished;


    public ReviewBestResponseDto(Long userId, int userImg, String userName, float userPointNumber,
                                String reviewContent, int likeCount, LocalDateTime createDate, Long toonId,
                                String toonTitle, String toonImg, String toonAuthor, String toonPlatform,
                                String toonWeekday, boolean finished) {
        this.userId = userId;
        this.userImg = userImg;
        this.userName = userName;
        this.userPointNumber = userPointNumber;
        this.reviewContent = reviewContent;
        this.likeCount = likeCount;
        this.createDate = createDate;
        this.toonId = toonId;
        this.toonTitle = toonTitle;
        this.toonImg = toonImg;
        this.toonAuthor = toonAuthor;
        this.toonPlatform = toonPlatform;
        this.toonWeekday = toonWeekday;
        this.finished = finished;
    }
}