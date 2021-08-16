package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.user.dto.response.UserInfoOnlyResponseDto;
import com.webtooni.webtooniverse.domain.user.dto.response.UserInfoResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BestReviewerWebtoonResponseDto {

    UserInfoOnlyResponseDto userInfoOnlyResponseDto;
    List<WebtoonAndGenreResponseDto> webtoonAndGenreResponseDtos;

    public BestReviewerWebtoonResponseDto(UserInfoOnlyResponseDto userInfoOnlyResponseDto,
        List<WebtoonAndGenreResponseDto> webtoonResponseDtoList) {
        this.userInfoOnlyResponseDto = userInfoOnlyResponseDto;
        this.webtoonAndGenreResponseDtos = webtoonResponseDtoList;
    }
}
