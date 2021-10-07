//package com.webtooni.webtooniverse.domain.review.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//import com.webtooni.webtooniverse.domain.review.domain.Review;
//import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
//import com.webtooni.webtooniverse.domain.review.dto.request.ReviewContentRequestDto;
//import com.webtooni.webtooniverse.domain.review.dto.request.WebtoonPointRequestDto;
//import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
//import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeRepository;
//import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeStatus;
//import com.webtooni.webtooniverse.domain.user.domain.User;
//import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
//import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
//import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
//import java.util.Optional;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class UnitReviewServiceTest {
//
//    @Mock
//    private ReviewRepository reviewRepository;
//
//    @Mock
//    private ReviewLikeRepository reviewLikeRepository;
//
//    @Mock
//    private WebtoonRepository webtoonRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private ReviewService reviewService;
//
//
//    /**
//     * 리뷰 수정 테스트
//     */
//    @DisplayName("리뷰 수정 테스트")
//    @Test
//    public void test1() {
//        //given
//        //임시 유저
//        User user = User.builder()
//            .userName("홍길동")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //웹툰 생성
//        Webtoon w1 = createWebtoon(25);
//        webtoonRepository.save(w1);
//
//        Review review1 = createReview(user, w1);
//
//        ReviewContentRequestDto requestDto = new ReviewContentRequestDto("변경될 내용");
//
//        given(reviewRepository.findById(1L)).willReturn(Optional.of(review1));
//
//        //when
//        reviewService.updateReview(1L, requestDto, user);
//
//        //then
//        assertThat(review1.getReviewContent()).isEqualTo(requestDto.getReviewContent());
//    }
//
//
//    /**
//     * 리뷰 삭제 테스트
//     */
//
//    @DisplayName("리뷰 삭제 테스트")
//    @Test
//    public void test2() {
//        //given
//        //임시 유저
//        User user = User.builder()
//            .userName("홍길동")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //웹툰 생성
//        Webtoon w1 = createWebtoon(25);
//        webtoonRepository.save(w1);
//
//        Review review1 = createReview(user, w1);
//
//        given(reviewRepository.findById(any())).willReturn(Optional.of(review1));
//        given(userRepository.findById(any())).willReturn(Optional.of(user));
//        given(reviewRepository.findWebtoonBtReviewId(any())).willReturn(w1);
//
//        //when
//        reviewService.deleteReview(any(), user);
//
//        //then
//        assertThat(review1.getReviewContent()).isNull();
//    }
//
//    /**
//     * 리뷰에 좋아요 테스트 - 처음 좋아요 - 좋아요 -> 취소 - 취소 -> 좋아요
//     */
//    @DisplayName("처음 누르는 사용자_리뷰에 좋아요")
//    @Test
//    public void test3() {
//        //given
//        //임시 유저
//        User user = User.builder()
//            .userName("홍길동")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //웹툰 생성
//        Webtoon w1 = createWebtoon(25);
//        webtoonRepository.save(w1);
//
//        Review review1 = createReview(user, w1);
//
//        given(reviewRepository.findById(any())).willReturn(Optional.of(review1));
//        given(reviewLikeRepository.findReviewLikeByReviewAndUser(review1, user)).willReturn(null);
//
//        //when
//        reviewService.clickReviewLike(any(), user);
//
//        //then
//        assertThat(review1.getLikeCount()).isEqualTo(2);
//
//    }
//
//    @DisplayName("좋아요->취소_리뷰에 좋아요")
//    @Test
//    public void test4() {
//        //given
//        //임시 유저
//        User user = User.builder()
//            .userName("홍길동")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //웹툰 생성
//        Webtoon w1 = createWebtoon(25);
//        webtoonRepository.save(w1);
//
//        Review review1 = createReview(user, w1);
//
//        ReviewLike reviewLike = ReviewLike.builder()
//            .reviewStatus(ReviewLikeStatus.LIKE)
//            .build();
//
//        given(reviewRepository.findById(any())).willReturn(Optional.of(review1));
//        given(reviewLikeRepository.findReviewLikeByReviewAndUser(review1, user))
//            .willReturn(reviewLike);
//
//        //when
//        reviewService.clickReviewLike(any(), user);
//
//        //then
//        assertThat(review1.getLikeCount()).isEqualTo(0);
//        assertThat(reviewLike.getReviewStatus()).isEqualTo(ReviewLikeStatus.CANCEL);
//
//    }
//
//    @DisplayName("취소->좋아요_리뷰에 좋아요")
//    @Test
//    public void test5() {
//        //given
//        //임시 유저
//        User user = User.builder()
//            .userName("홍길동")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //웹툰 생성
//        Webtoon w1 = createWebtoon(25);
//        webtoonRepository.save(w1);
//
//        Review review1 = createReview(user, w1);
//
//        ReviewLike reviewLike = ReviewLike.builder()
//            .reviewStatus(ReviewLikeStatus.CANCEL)
//            .build();
//
//        given(reviewRepository.findById(any())).willReturn(Optional.of(review1));
//        given(reviewLikeRepository.findReviewLikeByReviewAndUser(review1, user))
//            .willReturn(reviewLike);
//
//        //when
//        reviewService.clickReviewLike(any(), user);
//
//        //then
//        assertThat(review1.getLikeCount()).isEqualTo(2);
//        assertThat(reviewLike.getReviewStatus()).isEqualTo(ReviewLikeStatus.LIKE);
//
//    }
//
//    /**
//     * 웹툰에 별점 테스트
//     */
//    @DisplayName("웹툰에 별점 준적 없는 사용자")
//    @Test
//    public void getWebtoonPoint() {
//        //임시 유저
//        User user = User.builder()
//            .userName("홍길동")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //웹툰 생성
//        Webtoon w1 = createWebtoon(25);
//        webtoonRepository.save(w1);
//
//        Review review1 = createReview(user, w1);
//
//        WebtoonPointRequestDto webtoonPointRequestDto = new WebtoonPointRequestDto(1L, 1.0F);
//
//        given(webtoonRepository.findById(any())).willReturn(Optional.of(w1));
//        given(userRepository.findById(any())).willReturn(Optional.of(user));
//        given(reviewRepository.checkUserPointIsExist(w1, user)).willReturn(null);
//
//        //when
//        reviewService.clickWebtoonPointNumber(webtoonPointRequestDto, user);
//
//        //then
//        assertThat(w1.getTotalPointCount()).isEqualTo(26);
//        assertThat(w1.getToonAvgPoint()).isEqualTo(3.4F);
//
//    }
//
//    @DisplayName("웹툰에 별점 준적 있는 사용자")
//    @Test
//    public void getWebtoonPoint2() {
//        //given
//        //임시 유저
//        User user = User.builder()
//            .userName("홍길동")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //웹툰 생성
//        Webtoon w1 = createWebtoon(25);
//        webtoonRepository.save(w1);
//
//        Review review = createReview(user, w1);
//
//        WebtoonPointRequestDto webtoonPointRequestDto = new WebtoonPointRequestDto(1L, 1.0F);
//
//        given(webtoonRepository.findById(any())).willReturn(Optional.of(w1));
//        given(reviewRepository.checkUserPointIsExist(w1, user)).willReturn(review);
//
//        //when
//        reviewService.clickWebtoonPointNumber(webtoonPointRequestDto, user);
//
//        //then
//        assertThat(w1.getTotalPointCount()).isEqualTo(25);
//        assertThat(w1.getToonAvgPoint()).isEqualTo(3.48F);
//    }
//
//    private Review createReview(User user, Webtoon webtoon) {
//        return Review.builder()
//            .reviewContent("리뷰 내용1")
//            .userPointNumber((float) 1.5)
//            .likeCount(1)
//            .user(user)
//            .webtoon(webtoon)
//            .build();
//    }
//
//    private Webtoon createWebtoon(int totalPointCount) {
//        return Webtoon.builder()
//            .toonTitle("제목1")
//            .toonAuthor("작가1")
//            .toonContent("내용1")
//            .toonImg("이미지.png")
//            .realUrl("http://naver")
//            .toonPlatform("네이버")
//            .toonAvgPoint((float) 3.5)
//            .totalPointCount(totalPointCount)
//            .build();
//    }
//
//
//}
