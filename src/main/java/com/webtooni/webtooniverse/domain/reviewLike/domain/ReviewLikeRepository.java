package com.webtooni.webtooniverse.domain.reviewLike.domain;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike,Long> {

    List<ReviewLike> findAllByUser(User user);
    ReviewLike findReviewLikeByReviewAndUser(Review review, User user);

    @Query("select rl.review.id from ReviewLike rl where rl.user.id=:userId and rl.reviewStatus='LIKE'")
    List<Long> findReviewIdListByUser(@Param("userId") Long id);
}
