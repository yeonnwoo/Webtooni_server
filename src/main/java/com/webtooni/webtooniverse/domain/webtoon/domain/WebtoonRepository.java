package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.SimilarGenreToonDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long>, WebtoonRepositoryCustom {

    /**
     * 웹툰의 장르 찾기
     *
     * @param webtoon 찾고자하는 싶은 해당 웹툰
     * @return List<Genre> 장르 리스트
     */
    @Query("SELECT wg.genre from WebtoonGenre wg inner join wg.webtoon on wg.webtoon=:webtoon")
    List<Genre> findWebToonGenre(@Param("webtoon") Webtoon webtoon);

}
