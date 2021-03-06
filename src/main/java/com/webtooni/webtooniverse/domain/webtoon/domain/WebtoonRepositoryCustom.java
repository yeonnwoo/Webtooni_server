package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.RankTotalResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.SimilarGenreToonDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import java.util.List;

public interface WebtoonRepositoryCustom {

    List<RankTotalResponseDto> getTotalRank();

    List<Webtoon> getNaverRank();

    List<Webtoon> getKakaoRank();

    List<SimilarGenreToonDto> findSimilarWebtoonByGenre(String genre, Webtoon webtoon);

    List<WebtoonAndGenreResponseDto> findBestReviewerWebtoon(User user);

    User findBestReviewer();

    List<Webtoon> findUserGenreWebtoon(User user);

    List<WebtoonAndGenreResponseDto> findSimilarUserWebtoon(User user);

    List<WebtoonAndGenreResponseDto> findFinishedWebtoon();

    List<BestReviewerResponseDto> findBestReviewerForMain();

    List<WebtoonAndGenreResponseDto> findMyListWebtoon(String userName);

    List<WebtoonAndGenreResponseDto> findSearchedWebtoon(String keyword);

    List<Webtoon> getUnreviewedList();

    List<Review> br(Long userId);
}
