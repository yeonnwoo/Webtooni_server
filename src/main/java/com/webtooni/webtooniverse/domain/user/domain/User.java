package com.webtooni.webtooniverse.domain.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userImg;

    @Column(nullable = false)
    private String userGrade;

    @Column(nullable = true)
    private Long kakaoId;

    public User(String userName, String userEmail, String password, String userImg){
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.userImg = userImg;
        this.userGrade = "base";
        this.kakaoId = null;
    }

    public User(String password, Long kakaoId){
        this.password = password;
        this.userGrade = "base";
        this.kakaoId = kakaoId;
    }





}