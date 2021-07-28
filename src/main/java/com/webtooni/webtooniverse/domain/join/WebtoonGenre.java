package com.webtooni.webtooniverse.domain.join;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class WebtoonGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "GENRE_ID")
    Genre genre;

    @ManyToOne
    @JoinColumn(name = "WEBTOON_ID")
    Webtoon webtoon;
}
