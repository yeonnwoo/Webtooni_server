package com.webtooni.webtooniverse.domain.review.controller;


import com.webtooni.webtooniverse.domain.review.dto.request.ReviewContentRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.request.WebtoonPointRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.response.MyReviewResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewMainResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewNewResponseDto;
import com.webtooni.webtooniverse.domain.review.service.ReviewService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@RestController
public class ReviewController  {

    private final WebtoonRepository webtoonRepository;
    private final ReviewService reviewService;

    // to do(dto로 묶어서 보내주기)
    @GetMapping("reviews/new")
    public List<ReviewNewResponseDto> getNewReview() {
        return reviewService.getNewReview();
    }

    /**
     * TODO (dto로 묶어서 보내주기), service 거쳐서 가져오기 MVC
     */


    //메인페이지에 리뷰(최신순/ 베스트순) 불러오기
    @GetMapping("rank/reviews")
    public ReviewMainResponseDto getTotalReviews() {
        return reviewService.getMainReview();
    }

    //리뷰 작성(수정)
    @PutMapping("reviews/{id}")
    public Long updateReview(@PathVariable Long id, @RequestBody ReviewContentRequestDto reviewDto) {
        return reviewService.updateReview(id, reviewDto);
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

    @GetMapping("user/me/reviews")
    public List<MyReviewResponseDto> getMyReviews(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return reviewService.getMyReviews(userDetails.getUser());
    }

    //리뷰 삭제
    @DeleteMapping("reviews/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }

    //리뷰에 좋아요
    @PostMapping("reviews/{id}/like")
    public void clickReviewLike(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {

        //로그인된 유저 정보로 변경 되어야함
        User user = userDetails.getUser();

        reviewService.clickReviewLike(id, user);
    }

}
