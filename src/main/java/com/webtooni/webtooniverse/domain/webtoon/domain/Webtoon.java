package com.webtooni.webtooniverse.domain.webtoon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toon_id")
    private Long id;

    private String toonTitle;
    private String toonAuthor;
    private String toonContent;
    private String toonImg;
    private String toonWeekday;
    private String realUrl;
    private String toonAge;
    private String toonPlatform;
    private float toonAvgPoint;
    private int totalPointCount;
    private int reviewCount;
    private boolean finished;

    @Builder
    public Webtoon(String toonTitle, String toonAuthor, String toonContent, String toonImg,
        String toonWeekday,
        String realUrl, String toonAge, String toonPlatform, float toonAvgPoint,
        int totalPointCount, int reviewCount, boolean finished) {
        this.toonTitle = toonTitle;
        this.toonAuthor = toonAuthor;
        this.toonContent = toonContent;
        this.toonImg = toonImg;
        this.toonWeekday = toonWeekday;
        this.realUrl = realUrl;
        this.toonAge = toonAge;
        this.toonPlatform = toonPlatform;
        this.toonAvgPoint = toonAvgPoint;
        this.totalPointCount = totalPointCount;
        this.reviewCount = reviewCount;
        this.finished = finished;
    }

    /**
     * 별점을 처음 다는 유저
     * <p>
     * 별점을 달았을 때 총 별점 개수를 늘려준다.
     */
    public void changeToonPointTotalCount() {
        this.totalPointCount += 1;
    }

    /**
     * 별점을 처음 다는 유저
     * <p>
     * 평균 별점 점수 계산
     */
    public void changeToonAvgPoint(float userPoint) {
        float totalPoint = this.toonAvgPoint * this.totalPointCount + userPoint;

//        this.toonAvgPoint= (float) (Math.round(totalPoint/this.totalPointCount*100)/100.0);
        this.toonAvgPoint = Float
            .parseFloat(String.format("%.1f", totalPoint / (this.totalPointCount + 1)));

    }

    /**
     * 별점을 수정하려는 유저
     * <p>
     * - 별점 개수 변화 X - 평균 별점 점수 변경
     */
    public void updateToonAvgPoint(float originalUserPoint, float userPoint) {
        float totalPoint =
            this.toonAvgPoint * (this.totalPointCount) - originalUserPoint + userPoint;

//        this.toonAvgPoint = Float
//            .parseFloat(String.format("%.1f", totalPoint / this.totalPointCount));

        float Point = totalPoint / (float)this.totalPointCount;
        this.toonAvgPoint = (int) (Point * 100) / 100.0F;

    }

    public void plusReviewCount() {
        this.reviewCount += 1;
    }

    public void minusReviewCount() {
        this.reviewCount -= 1;
    }
}
