package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlatformRankResponseDto {

    private Long id;
    private String toonImg;
    private String toonTitle;
    private String toonAuthor;
    private float toonAvgPoint;
    private String toonPlatform;

    public PlatformRankResponseDto(Webtoon webtoon) {
        this.id = webtoon.getId();
        this.toonImg = webtoon.getToonImg();
        this.toonTitle = webtoon.getToonTitle();
        this.toonAuthor = webtoon.getToonAuthor();
        this.toonAvgPoint = webtoon.getToonAvgPoint();
        this.toonPlatform = webtoon.getToonPlatform();
    }
}
