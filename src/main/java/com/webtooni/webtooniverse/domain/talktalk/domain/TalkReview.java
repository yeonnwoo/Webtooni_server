package com.webtooni.webtooniverse.domain.talktalk.domain;

import com.webtooni.webtooniverse.domain.talktalk.dto.TalkReviewRequestDto;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.global.utils.TimeStamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class TalkReview extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "talk_post_id")
    private TalkPost talkPost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String commentContent;

    public TalkReview(TalkReviewRequestDto requestDto, User user){
        this.talkPost = requestDto.getTalkPost();
        this.commentContent = requestDto.getCommentContent();
        this.user = user;
    }

    public void update(TalkReviewRequestDto requestDto){
        this.commentContent = requestDto.getCommentContent();
    }
}
