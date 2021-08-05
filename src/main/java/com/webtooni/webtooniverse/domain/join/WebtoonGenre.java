package com.webtooni.webtooniverse.domain.join;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebtoonGenre {

    @Id
    @Column(name = "toon_genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toon_id")
    Webtoon webtoon;

    public WebtoonGenre(Genre genre, Webtoon webtoon) {
        this.genre = genre;
        this.webtoon = webtoon;
    }
}
