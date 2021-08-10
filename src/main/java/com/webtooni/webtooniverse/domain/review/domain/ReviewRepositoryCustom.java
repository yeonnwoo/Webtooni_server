package com.webtooni.webtooniverse.domain.review.domain;

import com.webtooni.webtooniverse.domain.review.dto.response.ReviewBestResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewNewResponseDto;
import java.util.List;

public interface ReviewRepositoryCustom {

    List<ReviewBestResponseDto> getBestReview();
    List<ReviewNewResponseDto> getNewReview();
}
