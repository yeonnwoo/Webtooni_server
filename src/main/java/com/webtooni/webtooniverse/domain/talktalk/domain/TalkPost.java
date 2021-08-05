package com.webtooni.webtooniverse.domain.talktalk.domain;

import com.webtooni.webtooniverse.domain.global.domain.Timestamped;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class TalkPost extends Timestamped {

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

    @Column(nullable = false)
    private Long likeNum;

    @Column(nullable = false)
    private Long talkReviewCount;

    public TalkPost(TalkPostRequestDto requestDto, User user) {
        this.postTitle = requestDto.getPostTitle();;
        this.postContent = requestDto.getPostContent();
        this.user = user;
        this.talkReviewCount = 0L;
    }

    public void update(TalkPostRequestDto talkPostRequestDto){
        this.postTitle = talkPostRequestDto.getPostTitle();
        this.postContent = talkPostRequestDto.getPostContent();
    }

    public void updateLikeNum(int count){
        this.likeNum += count;
    }

}
