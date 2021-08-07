package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoonGenre.WebtoonGenre;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MonthRankResponseDto {

    private Long id;
    private String toonImg;
    private String toonTitle;
    private String toonAuthor;
    private float toonAvgPoint;
    private String toonPlatform;
    private String toonWeekday;
    private boolean finished;
    private WebtoonGenre webtoonGenre;


    public MonthRankResponseDto(Webtoon webtoon) {
        this.id = webtoon.getId();
        this.toonImg = webtoon.getToonImg();
        this.toonTitle = webtoon.getToonTitle();
        this.toonAuthor = webtoon.getToonAuthor();
        this.toonAvgPoint = webtoon.getToonAvgPoint();
        this.toonPlatform = webtoon.getToonPlatform();
        this.toonWeekday = webtoon.getToonWeekday();
        this.finished = webtoon.isFinished();
        this.webtoonGenre = getWebtoonGenre();
    }
}
