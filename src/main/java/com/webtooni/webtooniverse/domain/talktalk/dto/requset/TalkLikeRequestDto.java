package com.webtooni.webtooniverse.domain.talktalk.dto.requset;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TalkLikeRequestDto {

    /**
     * TODO 사용여부 확인
     */
    private TalkPost talkPost;
    private User user;

}
