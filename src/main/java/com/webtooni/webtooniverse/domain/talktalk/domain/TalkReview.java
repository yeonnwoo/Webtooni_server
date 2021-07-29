package com.webtooni.webtooniverse.domain.talktalk.domain;

import com.webtooni.webtooniverse.domain.talktalk.dto.TalkReviewRequestDto;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class TalkReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long postTalkId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private String commentContent;

    public TalkReview(Long postTalkId, User user, String commentContent){
        this.postTalkId = postTalkId;
        this.user = user;
        this.commentContent = commentContent;
    }

    public TalkReview(TalkReviewRequestDto requestDto, User user){
        this.postTalkId = requestDto.getTalkPostId();
        this.commentContent = requestDto.getCommentContent();
        this.user = user;
    }

    public void update(TalkReviewRequestDto requestDto){
        this.commentContent = requestDto.getCommentContent();
    }
}
