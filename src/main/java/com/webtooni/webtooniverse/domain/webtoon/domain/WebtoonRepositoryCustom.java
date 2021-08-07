package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.BestReviewerResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface WebtoonRepositoryCustom {

    List<Webtoon> getTotalRank();
    List<Webtoon> getNaverRank();
    List<Webtoon> getKakaoRank();
    List<Webtoon> findSimilarWebtoonByGenre(String genre,Webtoon webtoon);
    List<Webtoon> findBestReviewerWebtoon(LocalDateTime startDate);
    List<Webtoon> findUserGenreWebtoon(User user);
    List<Webtoon> findSimilarUserWebtoon(User user);
    List<Webtoon> findFinishedWebtoon();
    List<BestReviewerResponseDto> findBestReviewerForMain();

}
