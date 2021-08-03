package com.webtooni.webtooniverse.domain.review.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.global.utils.TimeStamped;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String reviewContent;

    @Column
    float userPointNumber;

    @Column
    int likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toon_id")
    Webtoon webtoon;

    public Review(User user, Webtoon webtoon) {
        this.user = user;
        this.webtoon = webtoon;
    }

    public Review(User user, float userPointNumber, Webtoon webtoon) {
        this.user = user;
        this.userPointNumber = userPointNumber;
        this.webtoon = webtoon;
    }
}
