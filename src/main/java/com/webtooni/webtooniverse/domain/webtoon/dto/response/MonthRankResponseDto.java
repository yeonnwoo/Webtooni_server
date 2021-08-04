package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;


@Getter
public class MonthRankResponseDto {

    private Long id;
    private String toonImg;
    private String toonTitle;
    private String toonAuthor;
    private float toonAvgPoint;

    public MonthRankResponseDto(Webtoon webtoon) {
        this.id = webtoon.getId();
        this.toonImg = webtoon.getToonImg();
        this.toonTitle = webtoon.getToonTitle();
        this.toonAuthor = webtoon.getToonAuthor();
        this.toonAvgPoint = webtoon.getToonAvgPoint();
    }
}