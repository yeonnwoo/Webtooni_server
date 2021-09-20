package com.webtooni.webtooniverse.domain.talktalk.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TalkPostRequestDto {

    private String postTitle;
    private String postContent;

    @Builder
    public TalkPostRequestDto(String postTitle, String postContent) {
        this.postTitle = postTitle;
        this.postContent = postContent;
    }
}
