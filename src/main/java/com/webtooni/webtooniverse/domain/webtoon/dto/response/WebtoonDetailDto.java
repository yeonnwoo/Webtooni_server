package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.dto.response.WebtoonDetailReviewResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class WebtoonDetailDto {

    //내가 좋아요한 리뷰 게시글의 아이디 리스트
    private List<Long> userLikeReviewList= new ArrayList<>();
    private Boolean MyListOrNot;

    private String toonImg;
    private String toonTitle;
    private String toonAuthor;

    private List<String> toonGenre= new ArrayList<>();

    private String toonAge;

    private String realUrl;

    private float toonAvgPoint;

    private int totalPointCount;

    private String toonContent;

    private String toonWeekday;

    private String toonPlatform;

    private int reviewCount;

    private boolean finished;

    //전체 리뷰
    private List<WebtoonDetailReviewResponseDto> reviews;

    public WebtoonDetailDto(List<Long> reviewIdListByUser,boolean exists,Webtoon webtoon,List<String> genreList,List<WebtoonDetailReviewResponseDto> reviewList)
    {
        this.userLikeReviewList=reviewIdListByUser;
        this.MyListOrNot=exists;
        this.toonImg=webtoon.getToonImg();
        this.toonTitle=webtoon.getToonTitle();
        this.toonAuthor=webtoon.getToonAuthor();
        this.toonGenre=genreList;
        this.toonAge=webtoon.getToonAge();
        this.realUrl=webtoon.getRealUrl();
        this.totalPointCount=webtoon.getTotalPointCount();
        this.toonAvgPoint= webtoon.getToonAvgPoint();
        this.toonContent=webtoon.getToonContent();
        this.toonWeekday=webtoon.getToonWeekday();
        this.toonPlatform=webtoon.getToonPlatform();
        this.reviewCount=reviewList.size();
        this.finished=webtoon.isFinished();
        this.reviews=reviewList;
    }

}
