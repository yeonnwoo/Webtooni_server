package com.webtooni.webtooniverse.domain.review.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;

    @Column
    String reviewContent;

    @Column
    float starScore;

    @Column
    int likeCount;

    @ManyToOne
    @JoinColumn(name = "WEBTOON_ID")
    Webtoon webtoon;


}
