package com.webtooni.webtooniverse.domain.user.dto.response;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import lombok.Getter;

@Getter
public class BestReviewerResponseDto {

    private Long id;
    private int userImg;
    private String userName;
    private UserGrade userGrade;

    public BestReviewerResponseDto(User user) {
        this.id = user.getId();
        this.userImg = user.getUserImg();
        this.userName = user.getUserName();
        this.userGrade = user.getUserGrade();
    }
}
