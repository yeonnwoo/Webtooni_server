package com.webtooni.webtooniverse.domain.reviewLike.domain;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReviewLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Enumerated(EnumType.STRING)
    private ReviewLikeStatus reviewStatus;

    @Builder
    public ReviewLike(User user, Review review, ReviewLikeStatus reviewStatus) {
        this.user = user;
        this.review = review;
        this.reviewStatus = reviewStatus;
    }

    public static ReviewLike of(User user, Review review)
    {
        return new ReviewLike(user,review,ReviewLikeStatus.LIKE);
    }

    public void changeStatusLike() {
        this.reviewStatus= ReviewLikeStatus.LIKE;
    }

    public void changeStatusCancel() {
        this.reviewStatus= ReviewLikeStatus.CANCEL;
    }
}
