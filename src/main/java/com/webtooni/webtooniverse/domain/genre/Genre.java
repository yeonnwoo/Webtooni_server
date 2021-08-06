package com.webtooni.webtooniverse.domain.genre;

import javax.persistence.*;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre_type")
    private GenreType genreType;
}
