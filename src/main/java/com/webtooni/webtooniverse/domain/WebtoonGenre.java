package com.webtooni.webtooniverse.domain;

import com.webtooni.webtooniverse.domain.Genre.domain.Genre;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "WEBTOON_GENRE")
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

    public WebtoonGenre createWebToonGenre(Genre genre,Webtoon webtoon)
    {
        WebtoonGenre webtoonGenre = new WebtoonGenre();
        webtoonGenre.genre=genre;
        webtoonGenre.webtoon=webtoon;

        return webtoonGenre;
    }
}
