package com.webtoon.demo.domain.webtoon.dto.response;


import com.webtoon.demo.domain.webtoon.domain.Webtoon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimilarGenreToonDto {

    private String toonImg;
    private String toonTitle;
    private String toonAuthor;
    private String toonPlatform;
    private String toonWeekday;
    private Float toonAvgPoint;
    private int totalPointCount;

    public SimilarGenreToonDto(Webtoon webtoon)
    {
        this.toonImg= webtoon.getToonImg();
        this.toonTitle= webtoon.getToonTitle();
        this.toonAuthor= webtoon.getToonAuthor();
        this.toonPlatform= webtoon.getToonPlatform();
        this.toonWeekday=webtoon.getToonWeekday();
        this.toonAvgPoint= webtoon.getToonAvgPoint();
        this.totalPointCount= webtoon.getTotalPointCount();
    }
}
