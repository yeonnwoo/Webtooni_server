package com.webtooni.webtooniverse.domain.review.service;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewBestResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewMainResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewNewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    //리뷰 최신순 불러오기
    public ReviewMainResponseDto getMainReview(){
        List<Review> getRecentNewReviews = reviewRepository.getNewReview();
        List<Review> getRecentBestReviews = reviewRepository.getBestReview();

        List<ReviewNewResponseDto> collectNewReview = getRecentNewReviews.stream()
                .map(ReviewNewResponseDto::new)
                .collect(Collectors.toList());


        List<ReviewBestResponseDto> collectBestReview = getRecentBestReviews.stream()
                .map(ReviewBestResponseDto::new)
                .collect(Collectors.toList());

        return new ReviewMainResponseDto(collectBestReview, collectNewReview);

    }
}
