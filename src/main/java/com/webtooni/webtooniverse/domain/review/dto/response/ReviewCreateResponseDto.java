package com.webtooni.webtooniverse.domain.review.dto.response;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewCreateResponseDto {

    private LocalDateTime createDate;

    public ReviewCreateResponseDto(Review review) {
        this.createDate = review.getCreateDate();
    }
}
