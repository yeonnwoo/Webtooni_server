package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    private List<String> toonGenre = new ArrayList<>();



    public MonthRankResponseDto(Long id, String toonImg, String toonTitle, String toonAuthor, float toonAvgPoint,
                                String toonPlatform, String toonWeekday, boolean finished, List<String> genreList) {
        this.id = id;
        this.toonImg = toonImg;
        this.toonTitle = toonTitle;
        this.toonAuthor = toonAuthor;
        this.toonAvgPoint = toonAvgPoint;
        this.toonPlatform = toonPlatform;
        this.toonWeekday = toonWeekday;
        this.finished = finished;
        this.toonGenre = genreList;
    }
}
