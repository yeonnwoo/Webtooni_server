package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TalkPostResponseDto {

  private Long postId;
  private Long userId;
  private int userImg;
  private String userName;
  private UserGrade userGrade;
  private String postTitle;
  private String postContent;
  private int likeNum;
  private int talkCommentCount;
  private boolean ILike;
  private LocalDateTime createDate;

  public TalkPostResponseDto(TalkPost talkPost, boolean exists) {
    this.postId = talkPost.getId();
    this.postTitle = talkPost.getPostTitle();
    this.postContent = talkPost.getPostContent();
    this.likeNum = talkPost.getLikeNum();
    this.talkCommentCount = talkPost.getTalkCommentCount();
    this.userId = talkPost.getUser().getId();
    this.userImg = talkPost.getUser().getUserImg();
    this.userName = talkPost.getUser().getUserName();
    this.userGrade = talkPost.getUser().getUserGrade();
    this.ILike = exists;
    this.createDate = talkPost.getCreateDate();
  }
}


