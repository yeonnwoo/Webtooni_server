package com.webtooni.webtooniverse.domain.talktalk.dto.requset;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TalkPostRequestDto {
    /**
     * TODO 사용여부 확인
     */
    private String postTitle;
    private String postContent;

}
