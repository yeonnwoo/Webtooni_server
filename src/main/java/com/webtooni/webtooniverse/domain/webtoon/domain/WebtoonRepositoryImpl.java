package com.webtooni.webtooniverse.domain.webtoon.domain;

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
        QWebtoon w = new QWebtoon("w");
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
}
