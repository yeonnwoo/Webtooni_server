package com.webtooni.webtooniverse.domain.user.domain;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "user_genre")
public class UserGenre {

    @Id
    @GeneratedValue
    @Column(name = "user_genre_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;


    public UserGenre(User user, Genre genre){
        this.user = user;
        this.genre = genre;
    }

}
