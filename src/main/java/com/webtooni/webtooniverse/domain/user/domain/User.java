package com.webtooni.webtooniverse.domain.user.domain;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.dto.UserInfoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String userImg;

    @Column(name = "user_grade")
    private String userGrade;

    @Column(name = "kakao_id")
    private Long kakaoId;

    @OneToMany(mappedBy = "user")
    List<TalkPost> talkPosts = new ArrayList<TalkPost>();

    public User(String password, Long kakaoId){
        this.password = password;
        this.userGrade = "base";
        this.kakaoId = kakaoId;
    }
    public void update(UserInfoRequestDto requestDto){
        this.userImg = requestDto.getUserImg();
        this.userName = requestDto.getUserName();
    }






}