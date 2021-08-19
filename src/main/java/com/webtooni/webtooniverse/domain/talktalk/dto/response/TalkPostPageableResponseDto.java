package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TalkPostPageableResponseDto {

    private List<TalkPost> talkPosts;
    private Long postCount;

    public TalkPostPageableResponseDto(List<TalkPost> talkPosts, Long postCount) {
        this.talkPosts = talkPosts;
        this.postCount = postCount;
    }
}
