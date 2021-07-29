package com.webtooni.webtooniverse.domain.talktalk.domain;

import com.webtooni.webtooniverse.domain.talktalk.dto.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class TalkPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String postContent;

    @ManyToOne
    @JoinColumn (nullable = false)
    private User user;

    @Column(nullable = false)
    private Long likeNum;

    public TalkPost (String postTitle, String postContent, User user){
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.user = user;
    }

    public TalkPost(TalkPostRequestDto requestDto, User user) {
        this.postTitle = requestDto.getPostTitle();;
        this.postContent = requestDto.getPostContent();
        this.user = user;
    }

    public void update(TalkPostRequestDto talkPostRequestDto){
        this.postTitle = talkPostRequestDto.getPostTitle();
        this.postContent = talkPostRequestDto.getPostContent();
    }

    public void updateLikeNum(int count){
        this.likeNum += count;
    }

}
