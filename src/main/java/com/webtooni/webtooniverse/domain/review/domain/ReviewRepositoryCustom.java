package com.webtooni.webtooniverse.domain.review.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> getBestReview();
    List<Review> getNewReview();
    List<Review> findMyReviews(User user);
}
