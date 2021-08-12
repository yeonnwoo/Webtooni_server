package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TalkCommentResponseDto {
    private Long talkBoardCommentId;
    private TalkPost talkPost;
    private String commentContent;
    private User user;
    private String userName;
    private int userImg;
    private UserGrade userGrade;


    public TalkCommentResponseDto(TalkBoardComment talkBoardComment){
        this.talkBoardCommentId = talkBoardComment.getId();
        this.talkPost = talkBoardComment.getTalkPost();
        this.commentContent = talkBoardComment.getCommentContent();
        this.user = talkBoardComment.getUser();
        this.userImg = talkBoardComment.getUser().getUserImg();
        this.userName = talkBoardComment.getUser().getUserName();
        this.userGrade = talkBoardComment.getUser().getUserGrade();
    }
}
