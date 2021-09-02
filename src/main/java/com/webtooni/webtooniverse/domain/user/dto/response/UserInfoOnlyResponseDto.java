package com.webtooni.webtooniverse.domain.user.dto.response;

import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoOnlyResponseDto {

    private String userName;
    private int userImg;
    private int userGrade;
    private int userScore;

    public UserInfoOnlyResponseDto(User user) {
        this.userName = user.getUserName();
        this.userImg = user.getUserImg();
        this.userGrade = user.getUserGrade();
        this.userScore = user.getUserScore();
    }
}
