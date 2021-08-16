package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AllTalkPostPageResponseDto {

    private List<TalkPostPageResponseDto> posts;
    private Long postCount;


    public AllTalkPostPageResponseDto(List<TalkPostPageResponseDto> posts, Long postCount) {
        this.posts = posts;
        this.postCount = postCount;
    }
}
