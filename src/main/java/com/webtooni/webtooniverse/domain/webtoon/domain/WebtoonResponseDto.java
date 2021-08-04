package com.webtooni.webtooniverse.domain.webtoon.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebtoonResponseDto {
    Long id;
    String toonTitle;
    String toonAuthor;
    String toonContent;
    String toonImg;
    String toonWeekday;
    String realUrl;
    String toonAge;
    String toonFlatform;
    int totalPointCount;
    float toonAvgPoint;
    int reviewCount;
    boolean finished;

    public WebtoonResponseDto(Webtoon webtoon) {
        this.id = webtoon.getId();
        this.toonTitle = webtoon.getToonTitle();
        this.toonAuthor = webtoon.getToonAuthor();
        this.toonContent = webtoon.getToonContent();
        this.toonImg = webtoon.getToonImg();
        this.toonWeekday = webtoon.getToonWeekday();
        this.realUrl = webtoon.getRealUrl();
        this.toonAge = webtoon.getToonAge();
        this.toonFlatform = webtoon.getToonPlatform();
        this.toonAvgPoint = webtoon.getToonAvgPoint();
        this.reviewCount = webtoon.getReviewCount();
        this.finished = webtoon.isFinished();
        this.totalPointCount = webtoon.getTotalPointCount();
    }
}
