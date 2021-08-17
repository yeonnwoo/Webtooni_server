package com.webtooni.webtooniverse.domain.talktalk.domain;

import com.webtooni.webtooniverse.domain.talktalk.dto.request.TalkCommentRequestDto;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.global.utils.TimeStamped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TalkBoardComment extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "talk_comment_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "talk_post_id")
  private TalkPost talkPost;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "talk_comment")
  private String commentContent;

  public TalkBoardComment(TalkCommentRequestDto requestDto, User user, TalkPost talkPost) {
    this.commentContent = requestDto.getCommentContent();
    this.user = user;
    this.talkPost = talkPost;
  }

  public void update(TalkCommentRequestDto requestDto) {
    this.commentContent = requestDto.getCommentContent();
  }
}
