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
    @Column(name = "toon_id")
    private Long Id;

    private String toon_author;

    private String toon_title;

    private String toon_content;

    private String toon_img;

    private String toon_weekday;

    private float toon_avg_point;

    private String real_url;

    private String toon_age;

    private String toon_flatform;

    private int total_point_count;

    private int review_count;

    private int finished;




}
