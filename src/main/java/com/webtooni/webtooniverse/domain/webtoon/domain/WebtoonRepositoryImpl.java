package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


import java.util.List;

import static com.webtooni.webtooniverse.domain.webtoon.domain.QWebtoon.webtoon;

@RequiredArgsConstructor
public class WebtoonRepositoryImpl implements WebtoonRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    //이번달 웹투니버스 종합순위
    public List<Webtoon> getTotalRank(){
        return jpaQueryFactory.selectFrom(webtoon)
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(10)
                .fetch();

    }

    //네이버 웹툰 Top10
    public List<Webtoon> getNaverRank(){
        return jpaQueryFactory.selectFrom(webtoon)
                .where(webtoon.toonPlatform.eq("네이버"))
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(10)
                .fetch();
    }

    //카카오 웹툰 Top10
    public List<Webtoon> getKakaoRank(){
        return jpaQueryFactory.selectFrom(webtoon)
                .where(webtoon.toonPlatform.eq("카카오"))
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(10)
                .fetch();
    }
}
