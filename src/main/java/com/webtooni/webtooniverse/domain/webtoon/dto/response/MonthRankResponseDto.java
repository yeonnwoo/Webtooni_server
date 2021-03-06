package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MonthRankResponseDto {

    private Long toonId;
    private String toonImg;
    private String toonTitle;
    private String toonAuthor;
    private float toonAvgPoint;
    private String toonPlatform;
    private String toonWeekday;
    private boolean finished;
    private List<String> genres = new ArrayList<>();


    public MonthRankResponseDto(Long toonId, String toonImg, String toonTitle, String toonAuthor,
        float toonAvgPoint,
        String toonPlatform, String toonWeekday, boolean finished, List<String> genreList) {
        this.toonId = toonId;
        this.toonImg = toonImg;
        this.toonTitle = toonTitle;
        this.toonAuthor = toonAuthor;
        this.toonAvgPoint = toonAvgPoint;
        this.toonPlatform = toonPlatform;
        this.toonWeekday = toonWeekday;
        this.finished = finished;
        this.genres = genreList;
    }
}
