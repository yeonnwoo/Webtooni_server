package com.webtooni.webtooniverse.domain.webtoon.domain;

import java.util.List;

public interface WebtoonRepositoryCustom {

    List<Webtoon> getTotalRank();
    List<Webtoon> getNaverRank();
    List<Webtoon> getKakaoRank();
}