package com.webtooni.webtooniverse.domain.webtoon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Webtoon {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "toonId")
    private Long Id;

    private String toonTitle;

    private String toonAuthor;

    private String toonContent;

    private String toonImg;

    private String toonWeekday;

    private String realUrl;

    private String toonAge;

    private String toonFlatform;

    private float toonPointTotalNumber;

    private int reviewCount;

    private boolean finished;




}
