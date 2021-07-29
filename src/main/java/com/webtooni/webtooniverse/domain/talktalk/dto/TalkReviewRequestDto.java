package com.webtooni.webtooniverse.domain.talktalk.dto;

import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;

@Getter
public class TalkReviewRequestDto {

    private Long talkPostId;
    private User user;
    private String commentContent;


}
