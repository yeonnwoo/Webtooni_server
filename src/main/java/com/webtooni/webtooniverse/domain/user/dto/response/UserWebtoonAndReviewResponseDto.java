package com.webtooni.webtooniverse.domain.user.dto.response;

import com.webtooni.webtooniverse.domain.review.dto.response.MyReviewResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewWebtoonGenre;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserWebtoonAndReviewResponseDto {

    List<WebtoonAndGenreResponseDto> myWebtoons;
    List<ReviewWebtoonGenre> myReviews;
    UserInfoResponseDto userInfoResponseDto;

    public UserWebtoonAndReviewResponseDto(List<WebtoonAndGenreResponseDto> myWebtoonList,
        List<ReviewWebtoonGenre> myReviews, UserInfoResponseDto userInfoResponseDto) {
        this.myWebtoons = myWebtoonList;
        this.myReviews = myReviews;
        this.userInfoResponseDto = userInfoResponseDto;
    }
}
