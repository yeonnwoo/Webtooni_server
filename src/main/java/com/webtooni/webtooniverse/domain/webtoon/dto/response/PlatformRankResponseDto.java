package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlatformRankResponseDto {

    private Long toonId;
    private String toonImg;
    private String toonTitle;
    private String toonAuthor;
    private float toonAvgPoint;
    private String toonPlatform;
    private String toonWeekday;
    private boolean finished;

    public PlatformRankResponseDto(Webtoon webtoon) {
        this.toonId = webtoon.getId();
        this.toonImg = webtoon.getToonImg();
        this.toonTitle = webtoon.getToonTitle();
        this.toonAuthor = webtoon.getToonAuthor();
        this.toonAvgPoint = webtoon.getToonAvgPoint();
        this.toonPlatform = webtoon.getToonPlatform();
        this.toonWeekday = webtoon.getToonWeekday();
        this.finished = webtoon.isFinished();
    }
}
