package com.webtooni.webtooniverse.domain.talktalk.dto;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;

@Getter
public class TalkReviewRequestDto {

    /**
     * TODO 사용여부 확인
     */

    private TalkPost talkPost;
    private User user;
    private String commentContent;


}
