package com.webtooni.webtooniverse.domain.talktalk.dto;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;

@Getter
public class TalkReviewRequestDto {

    private TalkPost talkPost;
    private User user;
    private String commentContent;


}
