package com.webtooni.webtooniverse.domain.review.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    /**
     * 해당 유저가 특정 웹툰에 별점을 준 적이 있는지 체크한다. 해당 리뷰가 존재하지 않으면 처음이다.
     */
    @Query("select r from Review r join r.webtoon on r.webtoon=:webtoon and r.user=:user")
    Review checkUserPointIsExist(@Param("webtoon") Webtoon webtoon, @Param("user") User user);

    /**
     * 웹툰에 달린 리뷰 찾기
     *
     * @param toonId 해당 리뷰의 id
     * @return List<Review> 리뷰 리스트
     */
    @Query("select r from Review r inner join r.webtoon on r.webtoon.id=:toonId")
    List<Review> findReviewByWebToonId(@Param("toonId") Long toonId);


}
