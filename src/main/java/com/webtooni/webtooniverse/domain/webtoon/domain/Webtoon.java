package com.webtooni.webtooniverse.domain.webtoon.domain;

import javax.persistence.*;

@Entity
@Table(name = "webtoon")
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toon_id")
    private Long id;

    @Column(name = "toon_author")
    private String toonAuthor;

    @Column(name = "toon_title")
    private String toonTitle;

    @Column(name = "toon_content")
    private String toonContent;

    @Column(name = "toon_img")
    private String toonImg;

    @Column(name = "toon_avg_point")
    private Float toonAvgPoint;

    @Column(name = "reql_url")
    private String realUrl;

    @Column(name = "toon_age")
    private String toonAge;

    @Column(name = "toon_flatform")
    private String toonFlatform;

    @Column(name = "review_count")
    private Long reviewCount;
    // 기본 값을 0으로 가져가야 하지 않을까

    @Column(name = "finished")
    private String finished;

    @Column(name = "total_point_count")
    private int totalPointCount;


}
