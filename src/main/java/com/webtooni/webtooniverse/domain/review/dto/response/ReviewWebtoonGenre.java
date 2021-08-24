package com.webtooni.webtooniverse.domain.review.dto.response;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class ReviewWebtoonGenre {

    private Long reviewId;
    private String reviewContent;
    private Float userPointNumber;
    private int likeCount;
    private LocalDateTime createDate;
    private WebtoonAndGenreResponseDto webtoon;

    public ReviewWebtoonGenre(Review review, List<String> genres) {
        this.reviewId = review.getId();
        this.reviewContent = review.getReviewContent();
        this.userPointNumber = review.getUserPointNumber();
        this.likeCount = review.getLikeCount();
        this.createDate = review.getCreateDate();
        this.webtoon = new WebtoonAndGenreResponseDto(review.getWebtoon(), genres);
    }
}
