package com.webtooni.webtooniverse.domain.review.domain;

import com.webtooni.webtooniverse.domain.global.domain.Timestamped;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Review extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "toon_id")
    private Webtoon webtoon;

    @Lob
    @Column(name = "review_content")
    private String reviewContent;

    @Column(name = "user_point_number")
    private Float userPointNumber;

    @Column(name = "like_count")
    private int likeCount;

}
