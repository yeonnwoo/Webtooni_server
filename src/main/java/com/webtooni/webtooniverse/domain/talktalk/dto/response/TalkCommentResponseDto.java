package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TalkCommentResponseDto {

    private Long commentId;
    private Long postId;
    private String commentContent;
    private Long userId;
    private String userName;
    private int userImg;
    private int userGrade;
    private LocalDateTime createDate;


    public TalkCommentResponseDto(TalkBoardComment talkBoardComment) {
        this.commentId = talkBoardComment.getId();
        this.postId = talkBoardComment.getTalkPost().getId();
        this.commentContent = talkBoardComment.getCommentContent();
        this.userId = talkBoardComment.getUser().getId();
        this.userImg = talkBoardComment.getUser().getUserImg();
        this.userName = talkBoardComment.getUser().getUserName();
        this.userGrade = talkBoardComment.getUser().getUserGrade();
        this.createDate = talkBoardComment.getCreateDate();
    }
}
