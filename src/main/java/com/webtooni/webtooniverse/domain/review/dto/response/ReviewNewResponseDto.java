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
    private int likeCount;
    private LocalDateTime creatDate;


    public ReviewNewResponseDto(Long userId, int userImg, String userName, float userPointNumber,
                                String reviewContent, int likeCount, LocalDateTime creatDate) {
        this.userId = userId;
        this.userImg = userImg;
        this.userName = userName;
        this.userPointNumber = userPointNumber;
        this.reviewContent = reviewContent;
        this.likeCount = likeCount;
        this.creatDate = creatDate;
    }
}
