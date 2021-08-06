package com.webtooni.webtooniverse.domain.webtoon.domain;

import static com.webtooni.webtooniverse.domain.webtoon.domain.QWebtoon.webtoon;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtooni.webtooniverse.domain.webtoonGenre.QWebtoonGenre;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class WebtoonRepositoryImpl implements WebtoonRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Webtoon> findSimilarWebtoonByGenre(String genre,Webtoon webtoon) {
        QWebtoonGenre wg = new QWebtoonGenre("wg");

        return queryFactory
                .select(wg.webtoon)
                .from(wg)
                .join(wg.genre)
                .on(wg.genre.genreType.eq(genre),wg.webtoon.ne(webtoon))
                .orderBy(NumberExpression.random().asc())
                .limit(2)
                .fetch();
    }

    //이번달 웹투니버스 종합순위
    public List<Webtoon> getTotalRank(){
        return queryFactory.selectFrom(webtoon)
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(10)
                .fetch();

    }

    //네이버 웹툰 Top10
    public List<Webtoon> getNaverRank(){
        return queryFactory.selectFrom(webtoon)
                .where(webtoon.toonPlatform.eq("네이버"))
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(10)
                .fetch();
    }

    //카카오 웹툰 Top10
    public List<Webtoon> getKakaoRank() {
        return queryFactory.selectFrom(webtoon)
                .where(webtoon.toonPlatform.eq("카카오"))
                .orderBy(webtoon.toonAvgPoint.desc())
                .limit(10)
                .fetch();
    }
}
