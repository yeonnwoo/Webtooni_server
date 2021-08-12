package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class WebtoonAndGenreResponseDto {

    private Long id;
    private String toonTitle;
    private String toonAuthor;
    private String toonContent;
    private String toonImg;
    private String toonWeekday;
    private String realUrl;
    private String toonAge;
    private String toonPlatform;
    private float toonAvgPoint;
    private int totalPointCount;
    private int reviewCount;
    private boolean finished;
    private List<String> genres = new ArrayList<>();

    public WebtoonAndGenreResponseDto(Webtoon webtoon) {
        this.id = webtoon.getId();
        this.toonTitle = webtoon.getToonTitle();
        this.toonAuthor = webtoon.getToonAuthor();
        this.toonContent = webtoon.getToonContent();
        this.toonImg = webtoon.getToonImg();
        this.toonWeekday = webtoon.getToonWeekday();
        this.realUrl = webtoon.getRealUrl();
        this.toonAge = webtoon.getToonAge();
        this.toonPlatform = webtoon.getToonPlatform();
        this.toonAvgPoint = webtoon.getToonAvgPoint();
        this.totalPointCount = webtoon.getTotalPointCount();
        this.reviewCount = webtoon.getReviewCount();
        this.finished = webtoon.isFinished();
    }

    public void addGenre(String genre) {
        genres.add(genre);
    }
}
