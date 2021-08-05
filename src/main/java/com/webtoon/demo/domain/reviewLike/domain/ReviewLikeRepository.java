package com.webtoon.demo.domain.reviewLike.domain;


import com.webtoon.demo.domain.review.domain.Review;
import com.webtoon.demo.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike,Long> {

    ReviewLike findReviewLikeByReviewAndUser(Review review, User user);
}
