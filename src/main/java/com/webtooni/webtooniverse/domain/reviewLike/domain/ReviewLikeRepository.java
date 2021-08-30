package com.webtooni.webtooniverse.domain.reviewLike.domain;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {

    /**
     * 해당 review에 대해 사용자가 좋아요한 이력을 조회합니다.
     * @param review
     * @param user
     * @return ReviewLike 객체
     */
    ReviewLike findReviewLikeByReviewAndUser(Review review, User user);

    /**
     * 유저가 좋아요한 리뷰 목록을 조회합니다.
     * @param id userId
     * @return 좋아요한 리뷰 Id 목록
     */
    @Query("select rl.review.id from ReviewLike rl where rl.user.id=:userId and rl.reviewStatus='LIKE'")
    List<Long> findReviewIdListByUser(@Param("userId") Long id);
}
