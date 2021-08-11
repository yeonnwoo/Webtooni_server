package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@NoArgsConstructor
public class AllTalkPostResponseDto {

    private List<TalkPostPageResponseDto> posts;
    private Long postCount;

    public AllTalkPostResponseDto(List<TalkPostPageResponseDto> posts, Long postCount) {

        this.posts = posts;
        this.postCount = postCount;
    }
}
