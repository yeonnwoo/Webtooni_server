package com.webtooni.webtooniverse.domain.review.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewResponseDto {

    private Long userId;
    private int userImg;
    private String userName;
    private float userPointNumber;
    private String reviewContent;
    private Long toonId;
    private String toonTitle;
    private int userGrade;
    private String toonImg;
    private String toonAuthor;
    private String toonPlatform;
    private String toonWeekday;
    private boolean finished;
    private LocalDateTime createDate;
    private float toonAvgPoint;
    private int likeCount;
    private Long reviewId;
    private List<String> genres = new ArrayList<>();


    public ReviewResponseDto(Long userId, int userImg, String userName, float userPointNumber,
        String reviewContent,
        Long toonId, String toonTitle, int userGrade, String toonImg, String toonAuthor,
        String toonPlatform, String toonWeekday, boolean finished, LocalDateTime createDate,
        float toonAvgPoint, int likeCount, Long reviewId) {
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
        this.createDate = createDate;
        this.toonAvgPoint = toonAvgPoint;
        this.likeCount = likeCount;
        this.reviewId = reviewId;
    }

    public void addGenre(String genre) {
        genres.add(genre);
    }
}
