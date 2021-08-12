package com.webtooni.webtooniverse.domain.user.domain;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.user.dto.response.UserGenreResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGenreRepository extends JpaRepository<UserGenre, Long> {
    UserGenreResponseDto findByGenreAndUser(Genre genre, User user);
}
