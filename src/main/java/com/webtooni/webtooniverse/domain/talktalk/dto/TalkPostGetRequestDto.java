package com.webtooni.webtooniverse.domain.talktalk.dto;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class TalkPostGetRequestDto {

    public Long id;
    private List<TalkPost> talkPosts = new ArrayList<>();
    private User user;
    private String userImg;
    private String userName;
    private String userGrade;

    public TalkPostGetRequestDto(Long id, List<TalkPost> talkPosts, User user){
        this.id = id;
        this.talkPosts = talkPosts;
        this.user = user;
        this.userImg = user.getUserImg();
        this.userName = user.getUserName();
        this.userGrade = user.getUserGrade();
    }
}


