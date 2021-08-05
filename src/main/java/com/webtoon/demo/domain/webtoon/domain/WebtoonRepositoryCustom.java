package com.webtoon.demo.domain.webtoon.domain;

import java.util.List;

public interface WebtoonRepositoryCustom {
    List<Webtoon> findSimilarWebtoonByGenre(String genre,Webtoon webtoon);
}
