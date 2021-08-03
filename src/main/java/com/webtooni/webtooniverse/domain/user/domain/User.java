package com.webtooni.webtooniverse.domain.user.domain;

import com.webtooni.webtooniverse.domain.join.Genre;
import com.webtooni.webtooniverse.domain.join.MyList;
import com.webtooni.webtooniverse.domain.join.UserGenre;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.global.utils.TimeStamped;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
