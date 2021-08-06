package com.webtooni.webtooniverse.domain.user.dto;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import lombok.Getter;

import java.util.List;

@Getter
public class UserGenreRequestDto {
    private List<Genre> genres;
}
