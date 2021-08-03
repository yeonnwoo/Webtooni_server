package com.webtooni.webtooniverse.domain.webtoon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toonId")
    private Long id;

    private String toonTitle;

    private String toonAuthor;

    private String toonContent;

    private String toonImg;

    private String toonWeekday;

    private String realUrl;

    private String toonAge;

    private String toonFlatform;

    private float toonAvgPoint;

    private int totalPointCount;

    private int reviewCount;

    private boolean finished;

    @Builder
    public Webtoon(String toonTitle, String toonAuthor, String toonContent, String toonImg, String toonWeekday,
                   String realUrl, String toonAge, String toonFlatform, float toonAvgPoint, int totalPointCount,
                   int reviewCount, boolean finished) {
        this.toonTitle = toonTitle;
        this.toonAuthor = toonAuthor;
        this.toonContent = toonContent;
        this.toonImg = toonImg;
        this.toonWeekday = toonWeekday;
        this.realUrl = realUrl;
        this.toonAge = toonAge;
        this.toonFlatform = toonFlatform;
        this.toonAvgPoint = toonAvgPoint;
        this.totalPointCount = totalPointCount;
        this.reviewCount = reviewCount;
        this.finished = finished;
    }



}
