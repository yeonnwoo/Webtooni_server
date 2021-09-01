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
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewWebtoonGenre;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeRepository;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeStatus;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
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
    private final UserRepository userRepository;

    /**
     * 메인 페이지 최신순/베스트순 리뷰를 조회합니다.
     * @return 최신순/베스트순 리뷰 조회 목록 dto
     */
    @Transactional(readOnly = true)
    public ReviewMainResponseDto getMainReview() {
        List<ReviewResponseDto> getRecentBestReviews = reviewRepository
            .getBestOrNewReview(ReviewStatus.BEST);
        List<ReviewResponseDto> getRecentNewReviews = reviewRepository
            .getBestOrNewReview(ReviewStatus.NEW);
        return new ReviewMainResponseDto(getRecentBestReviews, getRecentNewReviews);
    }

    /**
     * 웹툰에 대한 리뷰를 작성합니다.
     * @param id        리뷰 id
     * @param reviewDto 리뷰의 내용이 담긴 Dto
     * @return 리뷰 id
     */
    public ReviewCreateResponseDto updateReview(Long id, ReviewContentRequestDto reviewDto, User user) {
        //해당 리뷰 찾기
        Review findReview = getFindReview(id);

        //review count+1
        Webtoon webtoon = reviewRepository.findWebtoonBtReviewId(id);
        if (findReview.getReviewContent() == null) {
            webtoon.plusReviewCount();
            //유저 점수 update
            User findUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저를 찾을 수 없습니다.")
            );
            findUser.addUserScore(2);
        }



        //리뷰 내용,날짜 변경
        findReview.changeReviewContent(reviewDto);
        return new ReviewCreateResponseDto(findReview);
    }

    /**
     * 리뷰를 삭제합니다.
     * @param id 리뷰의 id
     */
    public void deleteReview(Long id, User user) {
        //해당 리뷰 찾기
        Review findReview = getFindReview(id);
        findReview.deleteReview();

        //review count-1
        Webtoon webtoon = reviewRepository.findWebtoonBtReviewId(id);
        webtoon.minusReviewCount();

        //유저 점수 update
        User findUser = userRepository.findById(user.getId()).orElseThrow(
            () -> new NullPointerException("해당 유저를 찾을 수 없습니다.")
        );
        findUser.addUserScore(-2);
    }

    /**
     * 리뷰에 좋아요 버튼을 누릅니다.
     * @param id   리뷰 id
     * @param user 사용자 정보
     */
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
     * 특정 웹툰에 별점을 줍니다.
     * @param reviewStarDto 웹툰 id, 별점 점수가 담긴 Dto
     * @param user          유저 정보
     * @return ReviewStarRequestDto 리뷰 id
     */
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

            //별점 평균 점수 변경
            findWebtoon.changeToonAvgPoint(reviewStarDto.getUserPointNumber());

            //총 별점 개수 늘려주기
            findWebtoon.changeToonPointTotalCount();

            //웹툰,유저 정보 넣기
            review.insertWebToonAndUser(findWebtoon, user);

            //유저 점수 update
            User findUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저를 찾을 수 없습니다.")
            );
            findUser.addUserScore(1);

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

    /**
     * 전체 리뷰 목록을 최신순으로 조회합니다.
     * @param page 페이지 number
     * @param size 페이지 크기
     * @param userDetails 로그인한 유저 정보
     * @return 페이지네이션 된 리뷰 전체 목록
     */
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

    /**
     * 전체 리뷰 목록을 좋아요순으로 조회합니다.
     * @param page 페이지 number
     * @param size 페이지 크기
     * @param userDetails 로그인한 유저 정보
     * @return 페이지네이션 된 리뷰 전체 목록
     */
    @Transactional(readOnly = true)
    public ReviewLikeResponseDto getBestReview(UserDetailsImpl userDetails, int page, int size) {
        List<Long> likeReviewIdList;
        if (userDetails == null) {
            likeReviewIdList = null;
        } else {
            User user = userDetails.getUser();
            likeReviewIdList = reviewLikeRepository.findReviewIdListByUser(user.getId());
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        List<ReviewResponseDto> reviewDto = reviewRepository.getBestReviewWithPageable(pageable);
        return new ReviewLikeResponseDto(likeReviewIdList, reviewDto, reviewRepository.count());
    }

    /**
     * 유저가 작성한 리뷰를 조회합니다.
     * @param userName 유저 이름
     * @return 작성한 리뷰 목록을 담은 dto
     */
    @Transactional(readOnly = true)
    public List<MyReviewResponseDto> getMyReviews(String userName) {
        List<Review> myReviews = reviewRepository.findMyReviews(userName);
        return myReviews.stream()
            .map(MyReviewResponseDto::new)
            .collect(Collectors.toList());
    }

    /**
     * 유저가 작성한 리뷰를 조회합니다.(작성된 리뷰의 웹툰 장르 포함)
     * @param userName 유저 이름
     * @return 작성한 리뷰 목록을 담은 dto
     */
    @Transactional(readOnly = true)
    public List<ReviewWebtoonGenre> getMyReviewsAndGenre(String userName) {
        List<ReviewWebtoonGenre> myReviewsAndGenre = reviewRepository
            .findMyReviewsAndGenre(userName);
        return myReviewsAndGenre;
    }

    /**
     * reviewId로 리뷰를 조회합니다.
     * @param id reviewId
     * @return 조회된 리뷰
     */
    private Review getFindReview(Long id) {
        return reviewRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("해당 review id가 존재하지 않습니다.")
        );
    }
}





