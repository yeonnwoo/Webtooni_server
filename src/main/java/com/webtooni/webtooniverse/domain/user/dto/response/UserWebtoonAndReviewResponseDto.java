package com.webtooni.webtooniverse.domain.user.dto.response;

import com.webtooni.webtooniverse.domain.review.dto.response.MyReviewResponseDto;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserWebtoonAndReviewResponseDto {

    List<WebtoonResponseDto> myWebtoons;
    List<MyReviewResponseDto> myReviews;
    int userImg;
    UserGrade userGrade;

    public UserWebtoonAndReviewResponseDto(List<WebtoonResponseDto> myWebtoonList,
        List<MyReviewResponseDto> myReviews, User thisUser) {
        this.myWebtoons = myWebtoonList;
        this.myReviews = myReviews;
        this.userImg= thisUser.getUserImg();
        this.userGrade=thisUser.getUserGrade();
    }
}
