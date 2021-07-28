package com.webtooni.webtooniverse.domain.join;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;

    @ManyToOne
    @JoinColumn(name = "WEBTOON_ID")
    Webtoon webtoon;
}
