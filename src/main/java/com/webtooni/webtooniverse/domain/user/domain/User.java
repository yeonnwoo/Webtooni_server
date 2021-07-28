package com.webtooni.webtooniverse.domain.user.domain;

import com.webtooni.webtooniverse.domain.join.Genre;
import com.webtooni.webtooniverse.domain.join.MyList;
import com.webtooni.webtooniverse.domain.join.UserGenre;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class User {

    @Id
    @Column(name = "USER_ID")
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

    @OneToMany(mappedBy = "user")
    List<MyList> myLists;

    @OneToMany(mappedBy = "user")
    List<UserGenre> userGenres;
}
