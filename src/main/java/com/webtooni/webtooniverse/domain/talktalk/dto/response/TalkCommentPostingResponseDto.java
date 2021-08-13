package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TalkCommentPostingResponseDto {
    private Long commentId;
    private TalkPost postId;
    private User userId;
    private String commentContent;
    private LocalDateTime createDate;

    public TalkCommentPostingResponseDto(TalkBoardComment talkBoardComment){
        this.commentId = talkBoardComment.getId();
        this.postId = talkBoardComment.getTalkPost();
        this.userId = talkBoardComment.getUser();
        this.commentContent = talkBoardComment.getCommentContent();
        this.createDate = talkBoardComment.getCreateDate();
    }

}
