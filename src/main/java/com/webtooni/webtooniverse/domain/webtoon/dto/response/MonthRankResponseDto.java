package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MonthRankResponseDto {

    private Long id;
    private String toonImg;
    private String toonTitle;
    private String toonAuthor;
    private float toonAvgPoint;
    private String toonPlatform;
    private String toonWeekday;
    private boolean finished;
    private Genre genre;


    @QueryProjection
    public MonthRankResponseDto(Long id, String toonImg, String toonTitle, String toonAuthor, float toonAvgPoint,
                                String toonPlatform, String toonWeekday, boolean finished, Genre genre) {
        this.id = id;
        this.toonImg = toonImg;
        this.toonTitle = toonTitle;
        this.toonAuthor = toonAuthor;
        this.toonAvgPoint = toonAvgPoint;
        this.toonPlatform = toonPlatform;
        this.toonWeekday = toonWeekday;
        this.finished = finished;
        this.genre = genre;
    }
}
