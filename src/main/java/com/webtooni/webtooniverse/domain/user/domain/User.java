package com.webtooni.webtooniverse.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    private String userName;

    private String userEmail;

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
}