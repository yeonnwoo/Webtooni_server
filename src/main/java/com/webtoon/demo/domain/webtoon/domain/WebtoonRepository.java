package com.webtoon.demo.domain.webtoon.domain;


import com.webtoon.demo.domain.genre.domain.Genre;
import com.webtoon.demo.domain.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WebtoonRepository extends JpaRepository<Webtoon,Long>,WebtoonRepositoryCustom {

    /**
     * 웹툰의 장르 찾기
     * @param webtoon 찾고자하는 싶은 해당 웹툰
     * @return List<Genre> 장르 리스트
     */
    @Query("SELECT wg.genre from WebtoonGenre wg inner join wg.webtoon on wg.webtoon=:webtoon")
    List<Genre> findWebToonGenre(@Param("webtoon") Webtoon webtoon);

    /**
     * 웹툰에 달린 리뷰 찾기
     * @param toonId 해당 리뷰의 id
     * @return List<Review> 리뷰 리스트
     */
    @Query("select r from Review r inner join r.webtoon on r.webtoon.id=:toonId and r.reviewContent IS Not null")
    List<Review> findReviewByWebToonId(@Param("toonId") Long toonId);

}