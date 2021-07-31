package com.webtooni.webtooniverse.domain;

import com.webtooni.webtooniverse.domain.Genre.domain.Genre;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "webtoon_genre")
public class WebtoonGenre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toonGenreId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="genreId")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toonId")
    private Webtoon webtoon;

    @Builder
    public WebtoonGenre(Genre genre, Webtoon webtoon) {
        this.genre = genre;
        this.webtoon = webtoon;
    }

}
