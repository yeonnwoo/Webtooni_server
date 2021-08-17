package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AllTalkPostPageLikeResponseDto {

    private AllTalkPostPageResponseDto AllPostDto;
    private List<TalkLikeListResponseDto> likeListDto;

    public AllTalkPostPageLikeResponseDto(AllTalkPostPageResponseDto AllPostDto,
        List<TalkLikeListResponseDto> likeListDto) {
        this.AllPostDto = AllPostDto;
        this.likeListDto = likeListDto;
    }

}
