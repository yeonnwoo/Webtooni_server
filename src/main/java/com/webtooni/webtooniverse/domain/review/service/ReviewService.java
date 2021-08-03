package com.webtooni.webtooniverse.domain.review.service;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.review.dto.request.WebtoonPointRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewContentRequestDto;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeRepository;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeStatus;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final WebtoonRepository webtoonRepository;

    /**
     * 리뷰를 작성하는 기능을 제공하는 구현체입니다.
     *
     * @param id        리뷰 id
     * @param reviewDto 리뷰의 내용이 담긴 Dto
     * @return 리뷰 id
     */

    public Long updateReview(Long id, ReviewContentRequestDto reviewDto) {
        //해당 리뷰 찾기
        Review findReview = reviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id를 찾을 수 없습니다.")
        );

        //리뷰 내용 변경
        findReview.changeReviewContent(reviewDto);
        return findReview.getId();
    }

    /**
     * 리뷰를 삭제하는 기능을 제공하는 구현체입니다.
     *
     * @param id 리뷰의 id
     */
    public void deleteReview(Long id) {
        //해당 리뷰 찾기
        Review findReview = reviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id를 찾을 수 없습니다.")
        );
        findReview.deleteReview();
    }

    /**
     * 리뷰에 좋아요를 누르는 기능을 제공하는 구현체입니다.
     * <p>
     * 1.한번도 좋아요를 누르지 않은 유저의 경우
     * 1-1.전체 좋아요 +1
     * 1-2. ReviewLike에 사용자와 리뷰 정보를 추가한다.
     * <p>
     * 2.현재 유저가 좋아요를 누른 상태 ( 좋아요 -> 취소 )
     * 2-1. reviewStatus를 Like로 변경한다.
     * <p>
     * 3.현재 좋아요를 누르지 않은 상태 ( 취소 -> 좋아요 )
     * 3-1. reviewStatus를 CANCLE로 변경한다.
     *
     * @param id   리뷰 id
     * @param user 사용자 정보
     */

    public void clickReviewLike(Long id, User user) {
        //해당 게시물 조회
        Review findReview = reviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );

        ReviewLike reviewLike = reviewLikeRepository.findReviewLikeByReviewAndUser(findReview, user);


        if (reviewLike.getReviewStatus() == null) {
            //전체 카운트 +1
            findReview.plusLikeCount();

            //ReviewLike에 해당 유저와 리뷰 추가
            ReviewLike newReviewLike = ReviewLike.of(user, findReview);
            reviewLikeRepository.save(newReviewLike);

        } else if (reviewLike.getReviewStatus() == ReviewLikeStatus.CANCLE) {
            //전체 카운트 +1
            findReview.plusLikeCount();

            //좋아요 상태로 변경
            reviewLike.changeStatusLike();
        } else if (reviewLike.getReviewStatus() == ReviewLikeStatus.LIKE) {
            //전체 카운트 -1
            findReview.minusLikeCount();

            //취소 상태로 변경
            reviewLike.changeStatusCancel();
        }
    }

    /**
     * 특정 웹툰에 별점을 주는 기능을 제공하는 구현체입니다.
     * <p>
     * 1. 유저가 해당 웹툰에 별점을 준적이 없는 경우
     * 1-1. review 생성
     * 1-2. webtoon의 별점 개수 +1
     * 1-3. webtoon의 평균 별점 변경
     * <p>
     * 2. 유저가 해당 웹툰에 별점을 준 적이 있는 경우
     * 2-1. webtoon의 평균 별점 변경
     * 2-2. review의 유저 별점 점수 변경
     *
     * @param reviewStarDto 웹툰 id, 별점 점수가 담긴 Dto
     * @param user          유저 정보
     */
    public void clickWebtoonPointNumber(WebtoonPointRequestDto reviewStarDto, User user) {

        //해당 웹툰 찾기
        Webtoon findWebtoon = webtoonRepository.findById(reviewStarDto.getWebtoonId()).orElseThrow(
                () -> new IllegalArgumentException("해당 웹툰이 존재하지 않습니다")
        );

        Review findReview = reviewRepository.checkUserPointIsExist(findWebtoon, user);

        //존재하지 않음
        if (findReview == null) {

            Review review = Review.of(reviewStarDto.getUserPointNumber(),findWebtoon,user);

            //총 별점 개수 늘려주기
            findWebtoon.changeToonPointTotalNumber();

            //별점 평균 점수 변경
            findWebtoon.changeToonAvgPoint(reviewStarDto.getUserPointNumber());

            //웹툰,유저 정보 넣기
            review.insertWebToonAndUser(findWebtoon, user);

            reviewRepository.save(review);
        }

        //이미 존재함
        else {
            //유저의 변경전 별점 점수
            float originalUserPoint = findReview.getUserPointNumber();

            //웹툰 평균 별점 점수 변경(원래 점수, 변경될 점수)
            findWebtoon.updateToonAvgPoint(originalUserPoint, reviewStarDto.getUserPointNumber());

            //유저의 별점 점수 변경
            findReview.changeUserPoint(reviewStarDto.getUserPointNumber());

        }
    }

}
