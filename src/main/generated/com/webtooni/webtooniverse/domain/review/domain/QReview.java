package com.webtooni.webtooniverse.domain.review.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -1091095492L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.webtooni.webtooniverse.global.utils.QTimeStamped _super = new com.webtooni.webtooniverse.global.utils.QTimeStamped(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final StringPath reviewContent = createString("reviewContent");

    public final com.webtooni.webtooniverse.domain.user.domain.QUser user;

    public final NumberPath<Float> userPointNumber = createNumber("userPointNumber", Float.class);

    public final com.webtooni.webtooniverse.domain.webtoon.domain.QWebtoon webtoon;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.webtooni.webtooniverse.domain.user.domain.QUser(forProperty("user")) : null;
        this.webtoon = inits.isInitialized("webtoon") ? new com.webtooni.webtooniverse.domain.webtoon.domain.QWebtoon(forProperty("webtoon")) : null;
    }

}

