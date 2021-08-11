package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TalkPostPageResponseDto {

    private Long postId;
    private String postTitle;
    private String postContent;
    private Long userId;
    private int userImg;
    private String userName;
    private UserGrade userGrade;
    private LocalDateTime createDate;


    public TalkPostPageResponseDto(Long postId, String postTitle, String postContent, Long userId, int userImg,
                                   String userName, UserGrade userGrade, LocalDateTime createDate) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.userId = userId;
        this.userImg = userImg;
        this.userName = userName;
        this.userGrade = userGrade;
        this.createDate = createDate;
    }
}
