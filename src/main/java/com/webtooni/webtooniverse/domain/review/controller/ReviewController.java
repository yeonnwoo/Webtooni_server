package com.webtooni.webtooniverse.domain.review.controller;


import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewContentRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.request.WebtoonPointRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewMainResponseDto;
import com.webtooni.webtooniverse.domain.review.service.ReviewService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor
@RestController
public class ReviewController  {

    private final ReviewRepository reviewRepository;
    private final WebtoonRepository webtoonRepository;
    private final ReviewService reviewService;

    @GetMapping("/api/v1/reviews/new")
    public List<Review> getLatestReview() {
        List<Review> reviewList = reviewRepository.findAllByOrderByCreateDate();
        return reviewList;
    }
    //메인페이지에 리뷰(최신순/ 베스트순) 불러오기
    @GetMapping("/api/v1/rank/reviews")
    public ReviewMainResponseDto getTotalReviews() {
        return reviewService.getMainReview();
    }

    //리뷰 작성(수정)
    @PutMapping("reviews/{id}")
    public Long updateReview(@PathVariable Long id, @RequestBody ReviewContentRequestDto reviewDto) {
        return reviewService.updateReview(id, reviewDto);
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

    /**
     * 웹툰에 별점주기
     *
     * @param reviewStarDto 웹툰 id,userPointNumber 담은 dto
     */
    @PutMapping("reviews/star")
    public void updateStar(@RequestBody WebtoonPointRequestDto reviewStarDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {


        //로그인된 유저 정보로 변경 되어야함
        User user = userDetails.getUser();

        reviewService.clickWebtoonPointNumber(reviewStarDto, user);

    }
}
