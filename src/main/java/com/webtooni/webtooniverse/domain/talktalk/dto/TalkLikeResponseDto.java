package com.webtooni.webtooniverse.domain.talktalk.dto;

import lombok.Getter;

@Getter
public class TalkLikeResponseDto {

    private String result;

    public TalkLikeResponseDto(String result){
        this.result = result;
    }
}
