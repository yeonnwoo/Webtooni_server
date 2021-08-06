package com.webtooni.webtooniverse.domain.webtoon.domain;

import javax.persistence.*;

@Entity
@Table(name = "webtoon")
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toon_id")
    private Long id;

    private String toonTitle;

    private String toonAuthor;

    private String toonContent;

    private String toonImg;

    private String toonWeekday;

    private String realUrl;

    private String toonAge;

    private String toonPlatform;

    private float toonAvgPoint;

    private int totalPointCount;

    private int reviewCount;

    private boolean finished;

}
