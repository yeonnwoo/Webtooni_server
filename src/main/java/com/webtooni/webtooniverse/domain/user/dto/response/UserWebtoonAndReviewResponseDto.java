package com.webtooni.webtooniverse.domain.user.dto.response;

import com.webtooni.webtooniverse.domain.review.dto.response.MyReviewResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserWebtoonAndReviewResponseDto {
    List<WebtoonResponseDto> myWebtoons;
    List<MyReviewResponseDto> myReviews;

    public UserWebtoonAndReviewResponseDto(List<WebtoonResponseDto> myWebtoonList, List<MyReviewResponseDto> myReviews) {
        this.myWebtoons = myWebtoonList;
        this.myReviews = myReviews;
    }
}
