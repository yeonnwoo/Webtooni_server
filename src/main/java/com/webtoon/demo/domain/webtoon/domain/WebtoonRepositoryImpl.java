package com.webtoon.demo.domain.webtoon.domain;

import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoon.demo.domain.webtoonGenre.QWebtoonGenre;
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
}
