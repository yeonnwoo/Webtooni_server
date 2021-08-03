package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface WebtoonRepositoryCustom {
    List<Webtoon> findBestReviewerWebtoon(LocalDateTime startDate, LocalDateTime now);

    List<Webtoon> findUserGenreWebtoon(User user);

    List<Webtoon> findSimilarUserWebtoon(User user);

    List<Webtoon> findFinishedWebtoon();
}
