package com.webtooni.webtooniverse.domain.review.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewBestResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewNewResponseDto;
import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> findMyReviews(User user);
    List<ReviewBestResponseDto> getBestReview();
    List<ReviewNewResponseDto> getNewReview();
}
