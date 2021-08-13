package com.webtooni.webtooniverse.domain.review.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> findMyReviews(User user);
    List<ReviewResponseDto> getBestReview();
    List<ReviewResponseDto> getNewReview();
    List<ReviewResponseDto> getNewReviewWithPageable(Pageable pageable);
}
