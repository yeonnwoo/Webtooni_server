package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TalkPostPageResponseDto {

    private Long postId;
    private String postTitle;
    private String postContent;
    private Long userId;
    private int userImg;
    private String userName;
    private int userGrade;
    private LocalDateTime createDate;
    private int likeNum;
    private int talkCommentCount;


    public TalkPostPageResponseDto(Long postId, String postTitle, String postContent, Long userId,
        int userImg,
        String userName, int userGrade, LocalDateTime createDate, int likeNum,
        int talkCommentCount) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.userId = userId;
        this.userImg = userImg;
        this.userName = userName;
        this.userGrade = userGrade;
        this.createDate = createDate;
        this.likeNum = likeNum;
        this.talkCommentCount = talkCommentCount;
    }
}
