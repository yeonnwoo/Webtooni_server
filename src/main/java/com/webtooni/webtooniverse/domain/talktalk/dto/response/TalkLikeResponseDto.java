package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TalkLikeResponseDto {

    private String result;

    public TalkLikeResponseDto(String result){
        this.result = result;
    }
}
