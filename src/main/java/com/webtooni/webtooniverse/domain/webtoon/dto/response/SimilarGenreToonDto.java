package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    public SimilarGenreToonDto(Webtoon webtoon)
    {
        this.toonId= webtoon.getId();
        this.toonImg= webtoon.getToonImg();
        this.toonTitle= webtoon.getToonTitle();
        this.toonAuthor= webtoon.getToonAuthor();
        this.toonPlatform= webtoon.getToonPlatform();
        this.toonWeekday=webtoon.getToonWeekday();
        this.toonAvgPoint= webtoon.getToonAvgPoint();
        this.totalPointCount= webtoon.getTotalPointCount();

    }
}
