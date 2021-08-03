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
}
