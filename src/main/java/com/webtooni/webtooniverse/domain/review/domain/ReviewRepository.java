package com.webtooni.webtooniverse.domain.review.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    /**
     * 해당 유저가 특정 웹툰에 별점을 준 적이 있는지 체크한다.
     * 해당 리뷰가 존재하지 않으면 처음이다.
     */
    @Query("select r from Review r join r.webtoon on r.webtoon=:webtoon and r.user=:user")
    Review checkUserPointIsExist(@Param("webtoon")Webtoon webtoon, @Param("user")User user);



}
