package com.webtooni.webtooniverse.domain.user.dto.response;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGenreResponseDto {

  private User user;
  private Genre genre;

  private UserGenreResponseDto(User user, Genre genre) {
    this.user = user;
    this.genre = genre;
  }
}
