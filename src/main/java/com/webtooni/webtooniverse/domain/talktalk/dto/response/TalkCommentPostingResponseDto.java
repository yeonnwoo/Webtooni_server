package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TalkCommentPostingResponseDto {

  private Long commentId;
  private Long postId;
  private Long userId;
  private String commentContent;
  private LocalDateTime createDate;

  public TalkCommentPostingResponseDto(TalkBoardComment talkBoardComment) {
    this.commentId = talkBoardComment.getId();
    this.postId = talkBoardComment.getTalkPost().getId();
    this.userId = talkBoardComment.getUser().getId();
    this.commentContent = talkBoardComment.getCommentContent();
    this.createDate = talkBoardComment.getCreateDate();
  }

}
