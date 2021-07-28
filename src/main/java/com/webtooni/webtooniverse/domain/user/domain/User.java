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


    public User(String userName, String userEmail, String password, String userImg){
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.userImg = userImg;
        this.userGrade = "base";
    }



}