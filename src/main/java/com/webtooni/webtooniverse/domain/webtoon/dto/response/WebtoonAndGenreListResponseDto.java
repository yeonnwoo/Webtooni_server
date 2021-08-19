package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class WebtoonAndGenreListResponseDto {

    private List<WebtoonAndGenreResponseDto> webtoonAndGenreResponseDtoList;

    public WebtoonAndGenreListResponseDto(
        List<WebtoonAndGenreResponseDto> webtoonAndGenreResponseDtoList) {
        this.webtoonAndGenreResponseDtoList = webtoonAndGenreResponseDtoList;
    }
}
