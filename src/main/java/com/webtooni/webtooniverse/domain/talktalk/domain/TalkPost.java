package com.webtooni.webtooniverse.domain.talktalk.domain;

import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.global.utils.TimeStamped;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "talk_board")
public class TalkPost extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_post_id")
    private Long id;

    @Column(name = "talk_post_title")
    private String postTitle;

    @Column(name = "talk_post_content")
    private String postContent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int likeNum;

    private int talkCommentCount;

    public TalkPost(TalkPostRequestDto requestDto, User user) {
        this.postTitle = requestDto.getPostTitle();
        this.postContent = requestDto.getPostContent();
        this.user = user;
        this.talkCommentCount = 0;
        this.likeNum = 0;
    }

    public void update(TalkPostRequestDto talkPostRequestDto){
        this.postTitle = talkPostRequestDto.getPostTitle();
        this.postContent = talkPostRequestDto.getPostContent();
    }

    public void updateLikeNum(int count){
        this.likeNum += count;
    }

    public void updateTalkCommentNum(int count){
        this.talkCommentCount += count;
    }
}
