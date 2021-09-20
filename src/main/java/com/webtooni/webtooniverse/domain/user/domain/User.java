package com.webtooni.webtooniverse.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webtooni.webtooniverse.domain.user.dto.request.UserInfoRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.request.UserOnBoardingRequestDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "user_img")
    private int userImg;

    @Column(name = "user_grade")
    private int userGrade;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "user_score")
    private int userScore;

    public User(String password, String socialId){
        this.password = password;
        this.userGrade = 1;
        this.socialId = socialId;
    }

    public void update(UserInfoRequestDto requestDto) {
        this.userImg = requestDto.getUserImg();
        this.userName = requestDto.getUserName();
    }

    public void OnBoarding(UserOnBoardingRequestDto requestDto) {
        this.userImg = requestDto.getUserImg();
        this.userName = requestDto.getUserName();
    }

    @Builder
    public User(String userName, String password, int userImg, String socialId, int userScore) {
        this.userName = userName;
        this.password = password;
        this.userImg = userImg;
        this.userGrade = 1;
        this.socialId = socialId;
        this.userScore = userScore;
    }


    public User(String userName, int userImg) {
        this.userName = userName;
        this.userImg = userImg;
        this.userGrade = 1;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public void addUserScore(int userScore) {
        this.userScore += userScore;
        if (this.userScore < 400) {
            this.userGrade = (this.userScore / 100) + 1;
        } else {
            this.userGrade = 5;
        }

    }
}
