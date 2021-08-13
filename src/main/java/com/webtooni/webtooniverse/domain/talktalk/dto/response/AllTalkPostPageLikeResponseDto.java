package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;

@Getter
@NoArgsConstructor
public class AllTalkPostPageLikeResponseDto {

    private AllTalkPostPageResponseDto AllPostDto;
    private List<TalkLikeListResponseDto> likeListDto;

    public AllTalkPostPageLikeResponseDto(AllTalkPostPageResponseDto AllPostDto, List<TalkLikeListResponseDto> likeListDto){
        this.AllPostDto = AllPostDto;
        this.likeListDto = likeListDto;
    }

}
