package com.webtooni.webtooniverse.domain.talktalk.domain;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeStatus;
import com.webtooni.webtooniverse.domain.user.domain.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "talk_board_like")
public class TalkLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_board_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "talk_post_id")
    private TalkPost talkPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private TalkLikeStatus talkLikeStatus;

    @Builder
    public TalkLike(TalkPost talkPost, User user) {
        this.talkPost = talkPost;
        this.user = user;
    }

    @Builder
    public TalkLike(User user,TalkPost talkPost,
        TalkLikeStatus talkLikeStatus) {
        this.talkPost = talkPost;
        this.user = user;
        this.talkLikeStatus = talkLikeStatus;
    }

    public static TalkLike of(User user, TalkPost talkPost) {
        return new TalkLike(user, talkPost, TalkLikeStatus.LIKE);
    }

    public void changeStatusLike() {
        this.talkLikeStatus = TalkLikeStatus.LIKE;
    }

    public void changeStatusCancel() {
        this.talkLikeStatus = TalkLikeStatus.CANCEL;
    }

}
