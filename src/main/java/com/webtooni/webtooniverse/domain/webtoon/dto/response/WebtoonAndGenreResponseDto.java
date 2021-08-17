package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class WebtoonAndGenreResponseDto {

    private Long toonId;
    private String toonTitle;
    private String toonAuthor;
    private String toonImg;
    private String toonWeekday;
    private String toonPlatform;
    private float toonAvgPoint;
    private boolean finished;
    private final List<String> genres = new ArrayList<>();

    public WebtoonAndGenreResponseDto(Webtoon webtoon) {
        this.toonId = webtoon.getId();
        this.toonTitle = webtoon.getToonTitle();
        this.toonAuthor = webtoon.getToonAuthor();
        this.toonImg = webtoon.getToonImg();
        this.toonWeekday = webtoon.getToonWeekday();
        this.toonPlatform = webtoon.getToonPlatform();
        this.toonAvgPoint = webtoon.getToonAvgPoint();
        this.finished = webtoon.isFinished();
    }

    public WebtoonAndGenreResponseDto(Webtoon webtoon, List<String> genreList) {
        this.toonId = webtoon.getId();
        this.toonTitle = webtoon.getToonTitle();
        this.toonAuthor = webtoon.getToonAuthor();
        this.toonImg = webtoon.getToonImg();
        this.toonWeekday = webtoon.getToonWeekday();
        this.toonPlatform = webtoon.getToonPlatform();
        this.toonAvgPoint = webtoon.getToonAvgPoint();
        this.finished = webtoon.isFinished();
        genres.addAll(genreList);
    }

    public void addGenre(String genre) {
        genres.add(genre);
    }
}
