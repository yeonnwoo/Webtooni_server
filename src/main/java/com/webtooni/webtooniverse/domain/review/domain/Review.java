package com.webtooni.webtooniverse.domain.review.domain;

import com.webtooni.webtooniverse.domain.BaseTimeEntity;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewDto;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewContent;

    private float userPointNumber;

    private int likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toonId")
    private Webtoon webtoon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public Review(String reviewContent, float userPointNumber, int likeCount) {
        this.reviewContent = reviewContent;
        this.userPointNumber = userPointNumber;
        this.likeCount = likeCount;
    }
    /**
     * 처음 웹툰에 별점을 등록하는 경우엔 별점을 등록하고 Webtoon의 별점 개수를 +1해준다.
     */
    public Review(float userPointNumber, Webtoon webtoon, User user) {
        this.userPointNumber = userPointNumber;
        this.webtoon = webtoon;
        this.user = user;
    }

    /**
     * 기존에 별점이 존재하는 경우엔 별점을 수정한다.
     */
    public void changeUserPoint(float userPointNumber)
    {
        this.userPointNumber=userPointNumber;
    }

    /**
     *  Review에 User,Webtoon 정보 넣어준다.
     */

    public void insertWebToonAndUser(Webtoon webtoon, User user) {
        this.webtoon = webtoon;
        this.user = user;
    }

    /**
     * 리뷰를 작성(수정)한다.
     */
    public void changeContent(ReviewDto reviewDto) {
        this.reviewContent=reviewDto.getReviewContent();
    }
}
