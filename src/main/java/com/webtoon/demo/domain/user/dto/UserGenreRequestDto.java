package com.webtoon.demo.domain.user.dto;


import com.webtoon.demo.domain.genre.domain.Genre;
import lombok.Getter;

import java.util.List;

@Getter
public class UserGenreRequestDto {
    private List<Genre> genres;
}