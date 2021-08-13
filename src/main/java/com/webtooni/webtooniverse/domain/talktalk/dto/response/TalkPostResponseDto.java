package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TalkPostResponseDto {

    private Long TalkPostId;
    private User user;
    private int userImg;
    private String userName;
    private UserGrade userGrade;
    private String postTitle;
    private String postContent;
    private int likeNum;
    private int talkCommentCount;
    private boolean ILike;
    private List<TalkLikeListResponseDto> ILikeList;

    public TalkPostResponseDto(TalkPost talkPost, boolean exists, List<TalkLikeListResponseDto> ILikeList){
        this.ILikeList = ILikeList;
        this.TalkPostId = talkPost.getId();
        this.postTitle = talkPost.getPostTitle();
        this.postContent = talkPost.getPostContent();
        this.likeNum = talkPost.getLikeNum();
        this.talkCommentCount = talkPost.getTalkCommentCount();
        this.user = talkPost.getUser();
        this.userImg = talkPost.getUser().getUserImg();
        this.userName = talkPost.getUser().getUserName();
        this.userGrade = talkPost.getUser().getUserGrade();
        this.ILike = exists;
    }
}


