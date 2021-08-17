package com.webtooni.webtooniverse.domain.reviewLike.domain;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {

    ReviewLike findReviewLikeByReviewAndUser(Review review, User user);

    @Query("select rl.review.id from ReviewLike rl where rl.user.id=:userId and rl.reviewStatus='LIKE'")
    List<Long> findReviewIdListByUser(@Param("userId") Long id);
}
