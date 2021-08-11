package com.webtooni.webtooniverse.domain.user.dto.response;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginResponseDto {
    private Long userId;
    private String userName;
    private int userImg;
    private UserGrade userGrade;

    public UserLoginResponseDto(User user){
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.userGrade = user.getUserGrade();
        this.userImg = user.getUserImg();
    }
}
