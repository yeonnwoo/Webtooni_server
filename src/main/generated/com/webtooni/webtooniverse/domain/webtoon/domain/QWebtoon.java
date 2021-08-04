package com.webtooni.webtooniverse.domain.webtoon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWebtoon is a Querydsl query type for Webtoon
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWebtoon extends EntityPathBase<Webtoon> {

    private static final long serialVersionUID = 1620141844L;

    public static final QWebtoon webtoon = new QWebtoon("webtoon");

    public final BooleanPath finished = createBoolean("finished");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath realUrl = createString("realUrl");

    public final NumberPath<Integer> reviewCount = createNumber("reviewCount", Integer.class);

    public final StringPath toonAge = createString("toonAge");

    public final StringPath toonAuthor = createString("toonAuthor");

    public final NumberPath<Float> toonAvgPoint = createNumber("toonAvgPoint", Float.class);

    public final StringPath toonContent = createString("toonContent");

    public final StringPath toonImg = createString("toonImg");

    public final StringPath toonPlatform = createString("toonPlatform");

    public final StringPath toonTitle = createString("toonTitle");

    public final StringPath toonWeekday = createString("toonWeekday");

    public final NumberPath<Integer> totalPointCount = createNumber("totalPointCount", Integer.class);

    public QWebtoon(String variable) {
        super(Webtoon.class, forVariable(variable));
    }

    public QWebtoon(Path<? extends Webtoon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWebtoon(PathMetadata metadata) {
        super(Webtoon.class, metadata);
    }

}

