package com.webtooni.webtooniverse.domain.Genre.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "GENRE")
public class Genre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genreId")
    private Long id;

    private String genreType;

    @Builder
    public Genre(String genreType) {
        this.genreType = genreType;
    }
}
