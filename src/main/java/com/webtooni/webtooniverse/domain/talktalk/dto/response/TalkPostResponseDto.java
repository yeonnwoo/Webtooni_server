package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TalkPostResponseDto {

    private Long id;
    private User user;
    private int userImg;
    private String userName;
    private UserGrade userGrade;
    private String postTitle;
    private String postContent;

    public TalkPostResponseDto(TalkPost talkPost){
        this.id = talkPost.getId();
        this.postTitle = talkPost.getPostTitle();
        this.postContent = talkPost.getPostContent();
        this.user = talkPost.getUser();
        this.userImg = talkPost.getUser().getUserImg();
        this.userName = talkPost.getUser().getUserName();
        this.userGrade = talkPost.getUser().getUserGrade();
    }
}


