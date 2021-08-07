package com.webtooni.webtooniverse.domain.user.domain;

import com.webtooni.webtooniverse.domain.user.dto.UserInfoRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "user_img")
    private int userImg;

    @Enumerated(EnumType.STRING)
    private UserGrade userGrade;

    @Column(name = "kakao_id")
    private Long kakaoId;

    public User(String password, Long kakaoId){
        this.password = password;
        this.userGrade = UserGrade.valueOf("BASIC");
        this.kakaoId = kakaoId;
    }
    public void update(UserInfoRequestDto requestDto){
        this.userImg = requestDto.getUserImg();
        this.userName = requestDto.getUserName();
    }

    @Builder
    public User(String userName, String password, int userImg, UserGrade userGrade, Long kakaoId) {
        this.userName = userName;
        this.password = password;
        this.userImg = userImg;
        this.userGrade = userGrade;
        this.kakaoId = kakaoId;
    }

    public User(String userName,int userImg, UserGrade userGrade) {
        this.userName = userName;
        this.userImg = userImg;
        this.userGrade = userGrade;
    }

    public User(String userName){
        this.userName=userName;
    }
}
