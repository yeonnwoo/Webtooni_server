package com.webtoon.demo.domain.user.domain;


import com.webtoon.demo.domain.user.dto.UserInfoRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_img")
    private int userImg;

    @Enumerated(EnumType.STRING)
    private UserGrade userGrade;

    @Column(name = "kakao_id")
    private Long kakaoId;

    //feature 11 merge 후 주석 해제해야됨
//    @OneToMany(mappedBy = "user")
//    List<TalkPost> talkPosts = new ArrayList<TalkPost>();

    public User(String password, Long kakaoId){
        this.password = password;
        this.userGrade = UserGrade.THIRD;
        this.kakaoId = kakaoId;
    }
    public void update(UserInfoRequestDto requestDto){
        this.userImg = requestDto.getUserImg();
        this.userName = requestDto.getUserName();
    }

    @Builder
    public User(String userName, int userImg, UserGrade userGrade) {
        this.userName = userName;
        this.userImg = userImg;
        this.userGrade = userGrade;
    }

}