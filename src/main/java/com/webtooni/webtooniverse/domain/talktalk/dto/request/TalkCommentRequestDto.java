package com.webtooni.webtooniverse.domain.talktalk.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TalkCommentRequestDto {

    private String commentContent;

    public TalkCommentRequestDto(String commentContent) {
        this.commentContent = commentContent;
    }
}
