package com.webtooni.webtooniverse.domain.user.dto.response;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResponseDto {

    private String userName;
    private int userImg;
    private int userGrade;
    private int userScore;
    private List<String> genres = new ArrayList<>();

    public UserInfoResponseDto(User user, List<String> userGenre) {
        this.userName = user.getUserName();
        this.userImg = user.getUserImg();
        this.userGrade = user.getUserGrade();
        this.userScore = user.getUserScore();
        this.genres.addAll(userGenre);
    }
}
