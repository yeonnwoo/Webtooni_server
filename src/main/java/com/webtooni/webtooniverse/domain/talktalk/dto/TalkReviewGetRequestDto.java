package com.webtooni.webtooniverse.domain.talktalk.dto;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkReview;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;

public class TalkReviewGetRequestDto {
    private Long id;
    private TalkPost talkPost;
    private String commentContent;
    private User user;
    private String userName;
    private int userImg;
    private UserGrade userGrade;


    public TalkReviewGetRequestDto(TalkReview talkReview){
        this.id = talkReview.getId();
        this.talkPost = talkReview.getTalkPost();
        this.commentContent = talkReview.getCommentContent();
        this.user = talkReview.getUser();
        this.userImg = talkReview.getUser().getUserImg();
        this.userName = talkReview.getUser().getUserName();
        this.userGrade = talkReview.getUser().getUserGrade();
    }
}
