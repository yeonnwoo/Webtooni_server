package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.user.dto.response.UserInfoResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class BestReviewerWebtoonResponseDto {
    UserInfoResponseDto userInfoResponseDto;
    List<WebtoonAndGenreResponseDto> webtoonAndGenreResponseDtos;

    public BestReviewerWebtoonResponseDto(UserInfoResponseDto userInfoResponseDto, List<WebtoonAndGenreResponseDto> webtoonResponseDtoList) {
        this.userInfoResponseDto = userInfoResponseDto;
        this.webtoonAndGenreResponseDtos = webtoonResponseDtoList;
    }
}
