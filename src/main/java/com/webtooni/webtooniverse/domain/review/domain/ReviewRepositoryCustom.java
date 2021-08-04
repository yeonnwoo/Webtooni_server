package com.webtooni.webtooniverse.domain.review.domain;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> getBestReview();
    List<Review> getNewReview();
}
