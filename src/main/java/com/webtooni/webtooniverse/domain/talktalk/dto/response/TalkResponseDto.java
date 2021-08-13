package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TalkResponseDto {

    private String result;

    public TalkResponseDto(String result){
        this.result = result;
    }
}
