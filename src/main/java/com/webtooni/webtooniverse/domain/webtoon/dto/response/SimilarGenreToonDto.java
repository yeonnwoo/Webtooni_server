package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimilarGenreToonDto {

    private String toonImg;
    private String toonTitle;
    private String toonAuthor;
    private String toonFlatform;
    private String toonWeekday;
    private Float toonAvgPoint;
    private int totalPointCount;

    @Builder
    public SimilarGenreToonDto(String toonImg, String toonTitle, String toonAuthor, String toonFlatform,
                               String toonWeekday, Float toonAvgPoint, int totalPointCount) {
        this.toonImg = toonImg;
        this.toonTitle = toonTitle;
        this.toonAuthor = toonAuthor;
        this.toonFlatform = toonFlatform;
        this.toonWeekday = toonWeekday;
        this.toonAvgPoint = toonAvgPoint;
        this.totalPointCount = totalPointCount;
    }
}
