package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlatformRankListResponseDto {

    private List<PlatformRankResponseDto> platformRankResponseDtoList;

    public PlatformRankListResponseDto(
        List<PlatformRankResponseDto> platformRankResponseDtoList) {
        this.platformRankResponseDtoList = platformRankResponseDtoList;
    }
}
