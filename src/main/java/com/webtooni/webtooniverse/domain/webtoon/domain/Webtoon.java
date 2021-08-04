package com.webtooni.webtooniverse.domain.webtoon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Webtoon {

    @Id
    @Column(name = "toon_id")
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
    String toonAge;

    @Column
    String toonPlatform;

    @Column
    float toonAvgPoint;

    @Column
    int reviewCount;

    @Column
    boolean finished;

    @Column
    int totalPointCount;

    public Webtoon(String toonTitle) {
        this.toonTitle = toonTitle;
    }

    public Webtoon(String toonTitle, String toonAuthor, String toonContent, String toonImg,
                   String toonWeekday, String realUrl, String toonAge, String toonPlatform, float toonAvgPoint,
                   int reviewCount, boolean finished, int totalPointCount) {
        this.toonTitle = toonTitle;
        this.toonAuthor = toonAuthor;
        this.toonContent = toonContent;
        this.toonImg = toonImg;
        this.toonWeekday = toonWeekday;
        this.realUrl = realUrl;
        this.toonAge = toonAge;
        this.toonPlatform = toonPlatform;
        this.toonAvgPoint = toonAvgPoint;
        this.reviewCount = reviewCount;
        this.finished = finished;
        this.totalPointCount = totalPointCount;
    }
}
