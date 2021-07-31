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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review review;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    @Builder
    public ReviewLike(User user, Review review, ReviewStatus reviewStatus) {
        this.user = user;
        this.review = review;
        this.reviewStatus = reviewStatus;
    }

    //좋아요 상태로 변경
    public void changeStatusLike() {
        this.reviewStatus=ReviewStatus.LIKE;
    }

    public void changeStatusCancel() {
        this.reviewStatus=ReviewStatus.CANCLE;
    }
}
