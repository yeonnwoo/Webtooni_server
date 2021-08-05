package com.webtooni.webtooniverse.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;

    private String userEmail;

    private String password;

    private int userImg;

    @Enumerated(EnumType.STRING)
    private UserGrade userGrade;

    @Builder
    public User(String userName, String userEmail, int userImg, UserGrade userGrade) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userImg = userImg;
        this.userGrade = userGrade;
    }

    public User(String userName, String userEmail, String password, int userImg) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.userImg = userImg;
    }

}
