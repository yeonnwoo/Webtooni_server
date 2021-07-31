package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.Genre.domain.Genre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class WebtoonDetailDto {

    private String toonImg;
    private String toonTitle;
    private String toonAuthor;

    private List<Genre> toonGenre= new ArrayList<>();

    private String toonAge;

    private String realUrl;

    private float toonPointTotalNumber;

    private String toonContent;

    private String toonWeekday;

    private String toonFlatform;

    private int reviewCount;

    private boolean finished;

    //전체 리뷰
    private List<Review> reviews = new ArrayList<>();

    public WebtoonDetailDto(Webtoon webtoon,List<Genre> genreList,List<Review> reviewList)
    {

        this.toonImg=webtoon.getToonImg();
        this.toonTitle=webtoon.getToonTitle();
        this.toonAuthor=webtoon.getToonAuthor();

        this.toonGenre=genreList;

        this.toonAge=webtoon.getToonAge();
        this.realUrl=webtoon.getRealUrl();
        this.toonPointTotalNumber=webtoon.getToonPointTotalNumber();
        this.toonContent=webtoon.getToonContent();
        this.toonWeekday=webtoon.getToonWeekday();
        this.toonFlatform=webtoon.getToonFlatform();
        this.reviewCount=reviewList.size();
        this.finished=webtoon.isFinished();

        this.reviews=reviewList;
    }


}