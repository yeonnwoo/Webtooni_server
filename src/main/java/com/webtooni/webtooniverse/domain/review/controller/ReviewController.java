package com.webtooni.webtooniverse.domain.review.controller;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final WebtoonRepository webtoonRepository;

    @GetMapping("/api/v1/reviews/new")
    public List<Review> getLatestReview() {
        List<Review> reviewList = reviewRepository.findAllByOrderByCreatedAt();
        return reviewList;
    }

    @GetMapping("/api/v1/reviews/likes")
    public List<Review> getLikeReview() {
        List<Review> reviewList = reviewRepository.findAllByOrderByLikeCountDesc();
        return reviewList;
    }

    @GetMapping("api/v1/reviews/suggestion")
    public List<Webtoon> gerUnreviewdlist() {
        List<Webtoon> webtoonList = webtoonRepository.findByReviewCountLessThanEqual(5);
        return webtoonList;
    }
}
