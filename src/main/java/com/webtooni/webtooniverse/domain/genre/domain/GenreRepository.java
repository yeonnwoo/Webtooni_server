package com.webtooni.webtooniverse.domain.genre.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
    Genre findByGenreType(String genre);
}
