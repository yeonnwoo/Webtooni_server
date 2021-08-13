package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TalkPostPostingResponseDto {
    private Long postId;
    private String postTitle;
    private String postContent;
    private User userId;
    private int talkCommentCount;
    private int likeNum;
    private LocalDateTime createDate;

    public TalkPostPostingResponseDto(TalkPost talkPost){
        this.postId = talkPost.getId();
        this.postTitle = talkPost.getPostTitle();
        this.postContent = talkPost.getPostContent();
        this.userId = talkPost.getUser();
        this.talkCommentCount = talkPost.getTalkCommentCount();
        this.likeNum = talkPost.getLikeNum();
        this.createDate = talkPost.getCreateDate();
    }
}