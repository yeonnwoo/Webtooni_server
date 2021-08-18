package com.webtooni.webtooniverse.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webtooni.webtooniverse.domain.user.dto.request.UserInfoRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.request.UserOnBoardingRequestDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private UserGrade userGrade;

    @Column(name = "kakao_id")
    private Long kakaoId;

    @Column(name = "social_id")
    private String socialId;

    public User(String password, Long kakaoId, String socialId){
        this.password = password;
        this.userGrade = UserGrade.FIRST;
        this.kakaoId = kakaoId;
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
    public User(String userName, String password, int userImg, UserGrade userGrade, Long kakaoId, String socialId) {
        this.userName = userName;
        this.password = password;
        this.userImg = userImg;
        this.userGrade = userGrade;
        this.kakaoId = kakaoId;
        this.socialId = socialId;
    }


    public User(String userName, int userImg, UserGrade userGrade) {
        this.userName = userName;
        this.userImg = userImg;
        this.userGrade = userGrade;
    }

    public User(String userName) {
        this.userName = userName;
    }
}
