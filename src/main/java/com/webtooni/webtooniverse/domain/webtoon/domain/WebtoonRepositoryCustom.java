package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.MonthRankResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface WebtoonRepositoryCustom {

    List<MonthRankResponseDto> getTotalRank();
    List<Webtoon> getNaverRank();
    List<Webtoon> getKakaoRank();
    List<Webtoon> findSimilarWebtoonByGenre(String genre,Webtoon webtoon);
    List<Webtoon> findBestReviewerWebtoon(User user);
    User findBestReviewer(LocalDateTime startDate);
    List<Webtoon> findUserGenreWebtoon(User user);
    List<Webtoon> findSimilarUserWebtoon(User user);
    List<Webtoon> findFinishedWebtoon();
    List<BestReviewerResponseDto> findBestReviewerForMain();
    List<Webtoon> findMyListWebtoon(User user);

}
