package com.webtooni.webtooniverse.domain.webtoonGenre;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface WebtoonGenreRepository extends JpaRepository<WebtoonGenre, Long> {
}
