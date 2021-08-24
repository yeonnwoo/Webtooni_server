package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import java.util.ArrayList;
import java.util.List;

public class TestResponseDto {

    private Long toonId;
    private String toonTitle;
    private String toonAuthor;
    private String toonImg;
    private String toonWeekday;
    private String toonPlatform;
    private float toonAvgPoint;
    private boolean finished;
    private final List<String> genres = new ArrayList<>();

    public TestResponseDto(Webtoon webtoon, float toonAvgPoint) {
        this.toonId = webtoon.getId();
        this.toonTitle = webtoon.getToonTitle();
        this.toonAuthor = webtoon.getToonAuthor();
        this.toonImg = webtoon.getToonImg();
        this.toonWeekday = webtoon.getToonWeekday();
        this.toonPlatform = webtoon.getToonPlatform();
        this.toonAvgPoint = toonAvgPoint;
        this.finished = webtoon.isFinished();
    }

    public TestResponseDto(Webtoon webtoon, List<String> genreList, float toonAvgPoint) {
        this.toonId = webtoon.getId();
        this.toonTitle = webtoon.getToonTitle();
        this.toonAuthor = webtoon.getToonAuthor();
        this.toonImg = webtoon.getToonImg();
        this.toonWeekday = webtoon.getToonWeekday();
        this.toonPlatform = webtoon.getToonPlatform();
        this.toonAvgPoint = toonAvgPoint;
        this.finished = webtoon.isFinished();
        genres.addAll(genreList);
    }


    public void addGenre(String genre) {
        genres.add(genre);
    }
}
