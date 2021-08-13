package com.webtooni.webtooniverse.domain.myList;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.webtooni.webtooniverse.domain.myList.QMyList.myList;

@RequiredArgsConstructor
public class MyListRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;

    public boolean existsById(Long userId,Long webtoonId){
        return jpaQueryFactory.from(myList)
                .where(myList.user.id.eq(userId), myList.webtoon.id.eq(webtoonId))
                .select(myList.id)
                .fetchFirst()!=null;
    }

    public MyList findMyList(Long webtoonId,Long userId)
    {
        return jpaQueryFactory.selectFrom(myList)
                .where(myList.user.id.eq(userId),myList.webtoon.id.eq(webtoonId))
                .fetchOne();
    }
}
