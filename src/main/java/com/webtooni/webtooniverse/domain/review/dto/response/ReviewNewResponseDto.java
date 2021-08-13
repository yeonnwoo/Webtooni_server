package com.webtooni.webtooniverse.domain.review.dto.response;

import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewNewResponseDto {

    private Long userId;
    private int userImg;
    private String userName;
    private float userPointNumber;
    private String reviewContent;
    private Long toonId;
    private String toonTitle;
    private UserGrade userGrade;
    private String toonImg;
    private String toonAuthor;
    private String toonPlatform;
    private String toonWeekday;
    private boolean finished;
    private LocalDateTime creatDate;


    public ReviewNewResponseDto(Long userId, int userImg, String userName, float userPointNumber, String reviewContent,
                                Long toonId, String toonTitle, UserGrade userGrade, String toonImg, String toonAuthor,
                                String toonPlatform, String toonWeekday, boolean finished, LocalDateTime creatDate) {
        this.userId = userId;
        this.userImg = userImg;
        this.userName = userName;
        this.userPointNumber = userPointNumber;
        this.reviewContent = reviewContent;
        this.toonId = toonId;
        this.toonTitle = toonTitle;
        this.userGrade = userGrade;
        this.toonImg = toonImg;
        this.toonAuthor = toonAuthor;
        this.toonPlatform = toonPlatform;
        this.toonWeekday = toonWeekday;
        this.finished = finished;
        this.creatDate = creatDate;
    }
}
