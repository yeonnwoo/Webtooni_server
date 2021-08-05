package com.webtoon.demo.domain.webtoonGenre;


import com.webtoon.demo.domain.genre.domain.Genre;
import com.webtoon.demo.domain.webtoon.domain.Webtoon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class WebtoonGenre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toon_genre_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toon_id")
    private Webtoon webtoon;

    @Builder
    public WebtoonGenre(Genre genre, Webtoon webtoon) {
        this.genre = genre;
        this.webtoon = webtoon;
    }
}
