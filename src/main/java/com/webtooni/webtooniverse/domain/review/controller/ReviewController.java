package com.webtooni.webtooniverse.domain.review.controller;

import com.webtooni.webtooniverse.domain.review.dto.request.ReviewContentRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.request.WebtoonPointRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.response.MyReviewResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewCreateResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewLikeResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewMainResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewStarResponseDto;
import com.webtooni.webtooniverse.domain.review.service.ReviewService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import java.util.List;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 전체 리뷰 목록을 최신순으로 조회합니다.
     * @param page 페이지 number
     * @param size 페이지 크기
     * @param userDetails 로그인한 유저 정보
     * @return 페이지네이션 된 리뷰 전체 목록
     */
    @GetMapping("reviews/new")
    public ReviewLikeResponseDto getNewReview(@PathParam("page") int page,
        @PathParam("size") int size
        , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return reviewService.getNewReview(userDetails, page, size);
    }

    /**
     * 전체 리뷰 목록을 좋아요순으로 조회합니다.
     * @param page 페이지 number
     * @param size 페이지 크기
     * @param userDetails 로그인한 유저 정보
     * @return 페이지네이션 된 리뷰 전체 목록
     */
    @GetMapping("reviews/best")
    public ReviewLikeResponseDto getBestReview(@PathParam("page") int page,
        @PathParam("size") int size
        , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return reviewService.getBestReview(userDetails, page, size);
    }

    /**
     * 리뷰(베스트/최신순) 10개씩 조회합니다.
     * @return 베스트/최신순 리뷰 목록
     */
    @GetMapping("rank/reviews")
    public ReviewMainResponseDto getTotalReviews() {
        return reviewService.getMainReview();
    }

    /**
     * 웹툰에 대한 리뷰를 작성 또는 수정합니다.
     * @param id reviewId
     * @param reviewDto 리뷰 작성 내용을 담은 dto
     * @param userDetails 로그인한 유저 정보
     * @return 리뷰 작성 날짜
     */
    @PutMapping("reviews/{id}")
    public ReviewCreateResponseDto updateReview(@PathVariable Long id,
        @RequestBody ReviewContentRequestDto reviewDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        checkUser(userDetails);
        User user = userDetails.getUser();
        return reviewService.updateReview(id, reviewDto, user);
    }

    /**
     * 웹툰에 별점을 줍니다.
     * @param reviewStarDto 웹툰 id,userPointNumber 담은 dto
     * @return 리뷰 id
     */
    @PutMapping("reviews/star")
    public ReviewStarResponseDto updateStar(@RequestBody WebtoonPointRequestDto reviewStarDto
        , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        checkUser(userDetails);
        User user = userDetails.getUser();
        return reviewService.clickWebtoonPointNumber(reviewStarDto, user);
    }

    /**
     * 유저가 작성한 리뷰를 조회합니다.
     * @param userDetails 로그인한 유저 정보
     * @return 작성한 리뷰 내용들을 담은 dto
     */
    @GetMapping("user/me/reviews")
    public List<MyReviewResponseDto> getMyReviews(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        checkUser(userDetails);
        return reviewService.getMyReviews(userDetails.getUser().getUserName());
    }

    /**
     * 리뷰를 삭제합니다.
     * @param id          reviewId
     * @param userDetails 로그인한 유저 정보
     */
    @DeleteMapping("reviews/{id}")
    public void deleteReview(@PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        checkUser(userDetails);
        User user = userDetails.getUser();
        reviewService.deleteReview(id, user);
    }

    /**
     * 리뷰에 좋아요 버튼을 누릅니다.
     * @param id          review id
     * @param userDetails 로그인한 유저 정보
     */
    @PostMapping("reviews/{id}/like")
    public void clickReviewLike(@PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        checkUser(userDetails);
        User user = userDetails.getUser();
        reviewService.clickReviewLike(id, user);
    }

    private void checkUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다.");
        }

    }
}

