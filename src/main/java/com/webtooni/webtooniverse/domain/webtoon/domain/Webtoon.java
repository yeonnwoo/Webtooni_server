package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.webtooni.webtooniverse.domain.join.Genre;
import com.webtooni.webtooniverse.domain.join.WebtoonGenre;
import com.webtooni.webtooniverse.domain.review.domain.Reviews;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String toonTitle;

    @Column
    String toonAuthor;

    @Column
    String toonContent;

    @Column
    String toonImg;

    @Column
    String toonWeekday;

    @Column
    String realUrl;

    @Column
    Integer toonAge;

    @Column
    String toonFlatform;

    @Column
    float toonPointTotalNumber;

    @Column
    int reviewCount;

    @Column
    boolean finished;

    @OneToMany(mappedBy = "webtoon")
    List<WebtoonGenre> webtoonGenres;

    @OneToMany(mappedBy = "webtoon")
    List<Reviews> reviews;
}
