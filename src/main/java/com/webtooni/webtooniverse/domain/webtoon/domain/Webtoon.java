package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.webtooni.webtooniverse.domain.Genre.domain.Genre;
import com.webtooni.webtooniverse.domain.WebtoonGenre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toonId")
    private Long id;

    private String toonTitle;

    private String toonAuthor;

    private String toonContent;

    private String toonImg;

    private String toonWeekday;

    private String realUrl;

    private String toonAge;

    private String toonFlatform;

    private float toonAvgPoint;

    private int totalPointCount;

    private int reviewCount;

    private boolean finished;

    @Builder
    public Webtoon(String toonTitle, String toonAuthor, String toonContent, String toonImg, String toonWeekday,
                   String realUrl, String toonAge, String toonFlatform, float toonAvgPoint, int totalPointCount,
                   int reviewCount, boolean finished) {
        this.toonTitle = toonTitle;
        this.toonAuthor = toonAuthor;
        this.toonContent = toonContent;
        this.toonImg = toonImg;
        this.toonWeekday = toonWeekday;
        this.realUrl = realUrl;
        this.toonAge = toonAge;
        this.toonFlatform = toonFlatform;
        this.toonAvgPoint = toonAvgPoint;
        this.totalPointCount = totalPointCount;
        this.reviewCount = reviewCount;
        this.finished = finished;
    }

    @Builder
    public Webtoon(String toonTitle, String toonAuthor, String toonContent) {
        this.toonTitle = toonTitle;
        this.toonAuthor = toonAuthor;
        this.toonContent = toonContent;
    }

    /**
     * case : 별점을 처음 다는 유저
     * 별점을 달았을 때 총 별점 개수를 늘려준다.
     */
    public void changeToonPointTotalNumber() {
        this.totalPointCount += 1;
    }

    /**
     * case : 별점을 처음 다는 유저
     * 평균 별점 점수 계산
     */
    public void changeToonAvgPoint(float userPoint) {
        float totalPoint = this.toonAvgPoint * (this.totalPointCount - 1) + userPoint;

        this.toonAvgPoint= (float) (Math.round(totalPoint/this.totalPointCount*100)/100.0);

    }

    /**
     * case : 별점 수정하려는 유저
     * - 별점 개수 변화 X
     * - 평균 별점 점수 변경
     */
    public void updateToonAvgPoint(float originalUserPoint,float userPoint)
    {
        float totalPoint = this.toonAvgPoint *(this.totalPointCount) - originalUserPoint +userPoint;
        this.toonAvgPoint =(float) (Math.round(totalPoint/this.totalPointCount*100)/100.0);
    }


}
