package com.webtooni.webtooniverse.domain.review.domain;

import com.webtooni.webtooniverse.domain.review.dto.response.ReviewResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewWebtoonGenre;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    List<Review> findMyReviews(String userName);

    List<ReviewResponseDto> getBestOrNewReview(ReviewStatus reviewStatus);

    List<ReviewResponseDto> getNewReviewWithPageable(Pageable pageable);

    List<ReviewResponseDto> getBestReviewWithPageable(Pageable pageable);

    List<ReviewWebtoonGenre> findMyReviewsAndGenre(String userName);
}
