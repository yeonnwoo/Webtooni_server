package com.webtooni.webtooniverse.domain.review.dto.response;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewCreateResponseDto {

   private LocalDateTime createDate;


    public ReviewCreateResponseDto(Review review)
    {
        this.createDate = review.getCreateDate();
    }
}
