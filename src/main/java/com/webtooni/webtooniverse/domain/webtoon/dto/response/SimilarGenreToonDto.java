package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SimilarGenreToonDto {

    private Long toonId;
    private String toonImg;
    private String toonTitle;
    private String toonAuthor;
    private String toonPlatform;
    private String toonWeekday;
    private Float toonAvgPoint;
    private int totalPointCount;

}
