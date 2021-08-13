package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TalkLikeListResponseDto {

    private TalkPost talkPostId;

    public TalkLikeListResponseDto(TalkLike talkLike) {
        this.talkPostId = talkLike.getTalkPost();

    }

}
