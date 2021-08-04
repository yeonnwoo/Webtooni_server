package com.webtooni.webtooniverse.domain.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    String userName;

    @Column
    String userEmail;

    @Column
    int userImg;

    @Column
    String UserGrade;

    public User(String userName) {
        this.userName = userName;
    }
}
