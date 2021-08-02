package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.webtooni.webtooniverse.domain.Genre.domain.Genre;

import java.util.List;

public interface WebtoonRepositoryCustom {
    List<Webtoon> findSimilarWebtoonByGenre(String genre,Webtoon webtoon);
}
