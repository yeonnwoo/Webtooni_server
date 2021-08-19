package com.webtooni.webtooniverse.domain.review.service;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.review.domain.ReviewStatus;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewContentRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.request.WebtoonPointRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.response.MyReviewResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewCreateResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewLikeResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewMainResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewStarResponseDto;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeRepository;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeStatus;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.global.utils.LogExecutionTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final WebtoonRepository webtoonRepository;


    //리뷰 최신순,베스트순 불러오기
    @LogExecutionTime
    @Transactional(readOnly = true)
    public ReviewMainResponseDto getMainReview() {
        List<ReviewResponseDto> getRecentBestReviews = reviewRepository.getBestOrNewReview(
            ReviewStatus.BEST);
        List<ReviewResponseDto> getRecentNewReviews = reviewRepository.getBestOrNewReview(
            ReviewStatus.NEW);
        return new ReviewMainResponseDto(getRecentBestReviews, getRecentNewReviews);
    }

    /**
     * 리뷰를 작성한다.
     *
     * @param id        리뷰 id
     * @param reviewDto 리뷰의 내용이 담긴 Dto
     * @return 리뷰 id
     */
    public ReviewCreateResponseDto updateReview(Long id, ReviewContentRequestDto reviewDto) {
        //해당 리뷰 찾기
        Review findReview = getFindReview(id);
        //리뷰 내용,날짜 변경
        findReview.changeReviewContent(reviewDto);
        return new ReviewCreateResponseDto(findReview);
    }

    /**
     * 리뷰를 삭제한다.
     *
     * @param id 리뷰의 id
     */
    public void deleteReview(Long id) {
        //해당 리뷰 찾기
        Review findReview = getFindReview(id);
        findReview.deleteReview();
    }


    /**
     * 리뷰에 좋아요를 누른다.
     *
     * @param id   리뷰 id
     * @param user 사용자 정보
     */
    @LogExecutionTime
    public void clickReviewLike(Long id, User user) {
        //해당 게시물 조회
        Review findReview = getFindReview(id);

        ReviewLike reviewLike = reviewLikeRepository
            .findReviewLikeByReviewAndUser(findReview, user);

        if (reviewLike == null) {
            //전체 카운트 +1
            findReview.plusLikeCount();
            //ReviewLike에 해당 유저와 리뷰 추가
            ReviewLike newReviewLike = ReviewLike.of(user, findReview);
            reviewLikeRepository.save(newReviewLike);

        } else if (reviewLike.getReviewStatus() == ReviewLikeStatus.CANCEL) {
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
     * 특정 웹툰에 별점을 준다.
     *
     * @param reviewStarDto 웹툰 id, 별점 점수가 담긴 Dto
     * @param user          유저 정보
     * @return ReviewStarRequestDto 리뷰 id
     */
    @LogExecutionTime
    public ReviewStarResponseDto clickWebtoonPointNumber(WebtoonPointRequestDto reviewStarDto,
        User user) {

        //해당 웹툰 찾기
        Webtoon findWebtoon = webtoonRepository.findById(reviewStarDto.getToonId()).orElseThrow(
            () -> new IllegalArgumentException("해당 웹툰이 존재하지 않습니다")
        );

        Review findReview = reviewRepository.checkUserPointIsExist(findWebtoon, user);

        //존재하지 않음
        if (findReview == null) {

            Review review = Review.of(reviewStarDto.getUserPointNumber(), findWebtoon, user);

            //총 별점 개수 늘려주기
            findWebtoon.changeToonPointTotalCount();

            //별점 평균 점수 변경
            findWebtoon.changeToonAvgPoint(reviewStarDto.getUserPointNumber());

            //웹툰,유저 정보 넣기
            review.insertWebToonAndUser(findWebtoon, user);

            reviewRepository.save(review);
            return new ReviewStarResponseDto(review.getId(), findWebtoon.getToonAvgPoint());
        }

        //이미 존재함
        else {
            //유저의 변경전 별점 점수
            float originalUserPoint = findReview.getUserPointNumber();

            //웹툰 평균 별점 점수 변경(원래 점수, 변경될 점수)
            findWebtoon.updateToonAvgPoint(originalUserPoint, reviewStarDto.getUserPointNumber());

            //유저의 별점 점수 변경
            findReview.changeUserPoint(reviewStarDto.getUserPointNumber());

            return new ReviewStarResponseDto(findReview.getId(), findWebtoon.getToonAvgPoint());
        }
    }

    @Transactional(readOnly = true)
    public ReviewLikeResponseDto getNewReview(UserDetailsImpl userDetails, int page, int size) {

        List<Long> likeReviewIdList;
        if (userDetails == null) {
            likeReviewIdList = null;
        } else {
            User user = userDetails.getUser();
            likeReviewIdList = reviewLikeRepository.findReviewIdListByUser(user.getId());
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        List<ReviewResponseDto> reviewDto = reviewRepository.getNewReviewWithPageable(pageable);
        return new ReviewLikeResponseDto(likeReviewIdList, reviewDto, reviewRepository.count());

    }

    @Transactional(readOnly = true)
    public List<MyReviewResponseDto> getMyReviews(String userName) {
        List<Review> myReviews = reviewRepository.findMyReviews(userName);
        return myReviews.stream()
            .map(MyReviewResponseDto::new)
            .collect(Collectors.toList());
    }

    private Review getFindReview(Long id) {
        return reviewRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("해당 review id가 존재하지 않습니다.")
        );
    }
}





