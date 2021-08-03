package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MonthRankResponseDto {

    private Long id;
    private String toonImg;
    private String toonTitle;
    private String toonAuthor;
    private float toonAvgPoint;

    public MonthRankResponseDto(Webtoon webtoon) {
        this.id = webtoon.getId();
        this.toonImg = webtoon.getToon_img();
        this.toonTitle = webtoon.getToon_title();
        this.toonAuthor = webtoon.getToon_author();
        this.toonAvgPoint = webtoon.getToon_avg_point();
    }
}
