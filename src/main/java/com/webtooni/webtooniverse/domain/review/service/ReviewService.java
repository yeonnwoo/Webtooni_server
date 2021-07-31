package com.webtooni.webtooniverse.domain.review.service;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.review.dto.ReviewStarDto;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewDto;
import com.webtooni.webtooniverse.domain.reviewLike.ReviewLikeRepository;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewStatus;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewLikeRepository reviewLikeRepository;

    private final WebtoonRepository webtoonRepository;

    /**
     * @param - 리뷰 id
     * @param - 리뷰 내용
     * @return - 리뷰 id
     * 리뷰를 수정한다. (= 등록한다.)
     */

    public void updateReview(Long id, ReviewDto reviewDto) {
        //해당 리뷰 찾기
        Review findReview = reviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id를 찾을 수 없습니다.")
        );

        //리뷰 변경
        findReview.changeContent(reviewDto);
    }

    /**
     * 리뷰를 삭제한다.
     */
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    /**
     * 리뷰에 좋아요를 누른다.
     * @param - 리뷰 id
     *
     * 1.현재 좋아요를 누른 상태면 리뷰의 likeCount -=1
     * 2.현재 좋아요를 누르지 않은 상태면 리뷰의 likeCount +=1
     * 2-1. ReviewLike에 해당 user가 존재하지 않거나 ReviewStatus가 0인 경우
     */
    public void clickReviewLike(Long id) {
        //해당 게시물 조회
        Review findReview = reviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );

        /**
         * 임시 유저 : (현재, 로그인 정보 없음)
         */
        User user = User.builder()
                .userEmail("이메일")
                .userName("닉네임")
                .userGrade(UserGrade.FIRST)
                .userImg(1)
                .build();

        /**
         * 현재 로그인된 사용자(user)가 좋아요 누른 상태인지 확인
         */
        ReviewLike reviewLike = reviewLikeRepository.findReviewLikeByReviewAndUser(findReview, user);

        /**
         * 1.해당 웹툰에 대해 유저가 좋아요를 누른적이 없는 경우
         * 1-1.전체 좋아요를 하나 늘린다.
         * 1-2. ReviewLike에 사용자와 리뷰 정보를 추가한다.
         */
        if(reviewLike.getReviewStatus()== null)
        {
            //전체 카운트 +1
            findReview.plusLikeCount();

            //ReviewLike에 해당 유저와 리뷰 추가
            ReviewLike newReviewLike = ReviewLike.builder()
                    .review(findReview)
                    .user(user)
                    .reviewStatus(ReviewStatus.LIKE)
                    .build();

            reviewLikeRepository.save(newReviewLike);
        }


        /**
         * 2.취소->좋아요인 경우
         * 2-1. reviewStatus를 Like로 변경한다.
          */

        else if(reviewLike.getReviewStatus() == ReviewStatus.CANCLE)
        {
            //전체 카운트 +1
            findReview.plusLikeCount();

            //좋아요 상태로 변경
            reviewLike.changeStatusLike();
        }

        /**
         * 3.좋아요 -> 취소인 경우
         * 3-1. reviewStatus를 CANCLE로 변경한다.
         */
        else if(reviewLike.getReviewStatus() == ReviewStatus.LIKE)
        {
            //전체 카운트 -1
            findReview.minusLikeCount();

            //취소 상태로 변경
            reviewLike.changeStatusCancel();

        }

    }


    /**
     * 웹툰에 별점을 준다. = 수정하는 것과 동일
     */
    public void updateUserPointNumber(ReviewStarDto reviewStarDto) {

        /**
         * 임시 유저 : (현재, 로그인 정보 없음)
         */
        User user = User.builder()
                .userEmail("이메일")
                .userName("닉네임")
                .userGrade(UserGrade.FIRST)
                .userImg(1)
                .build();

        //해당 웹툰 찾기
        Webtoon findWebtoon = webtoonRepository.findById(reviewStarDto.getWebtoonId()).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다")
        );

        /**
         * 유저가 해당 웹툰에 별점을 준적이 있는지 체크한다.
         * 해당 유저의 FK와 해당 웹툰 FK를 가진 리뷰가 존재하는지 확인한다.
         * 별점을 주는 즉시 리뷰 테이블이 생성되기 때문에 존재 유무만 확인해도된다.
         */
        Review findReview = reviewRepository.checkUserPointIsFirst(findWebtoon, user);

        //존재하지 않음
        if(findReview==null)
        {
            Review review = Review.builder()
                    .userPointNumber(reviewStarDto.getUserPointNumber())
                    .build();

            //총 별점 개수 늘려주기
            findWebtoon.changeToonPointTotalNumber();
            review.insertWebToonAndUser(findWebtoon,user);

        }

        //이미 존재함
        else
        {
            findReview.changeUserPoint(reviewStarDto.getUserPointNumber());
        }


    }

}
