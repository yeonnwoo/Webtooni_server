package com.webtooni.webtooniverse.domain.user.dto.response;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResponseDto {
    private String userName;
    private int userImg;
    private UserGrade userGrade;

    public UserInfoResponseDto(User user) {
        this.userName = user.getUserName();
        this.userImg = user.getUserImg();
        this.userGrade = user.getUserGrade();
    }
}
