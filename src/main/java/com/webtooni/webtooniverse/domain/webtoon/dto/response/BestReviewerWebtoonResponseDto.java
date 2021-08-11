package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.user.dto.response.UserInfoResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class BestReviewerWebtoonResponseDto {
    UserInfoResponseDto userInfoResponseDto;
    List<WebtoonResponseDto> webtoonResponseDtoList;

    public BestReviewerWebtoonResponseDto(UserInfoResponseDto userInfoResponseDto, List<WebtoonResponseDto> webtoonResponseDtoList) {
        this.userInfoResponseDto = userInfoResponseDto;
        this.webtoonResponseDtoList = webtoonResponseDtoList;
    }
}
