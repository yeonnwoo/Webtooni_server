package com.webtooni.webtooniverse.domain.join;

import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "GENRE_ID")
    Genre genre;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;
}
