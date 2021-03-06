//package com.webtooni.webtooniverse.domain.review.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.webtooni.webtooniverse.domain.review.domain.Review;
//import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
//import com.webtooni.webtooniverse.domain.review.dto.request.ReviewContentRequestDto;
//import com.webtooni.webtooniverse.domain.review.dto.request.WebtoonPointRequestDto;
//import com.webtooni.webtooniverse.domain.review.dto.response.ReviewMainResponseDto;
//import com.webtooni.webtooniverse.domain.review.dto.response.ReviewResponseDto;
//import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
//import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeRepository;
//import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeStatus;
//import com.webtooni.webtooniverse.domain.user.domain.User;
//import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
//import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
//import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
//import java.util.List;
//import javax.transaction.Transactional;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@Transactional
//@SpringBootTest
//class ReviewServiceTest {
//
//    @Autowired
//    private ReviewService reviewService;
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Autowired
//    private ReviewLikeRepository reviewLikeRepository;
//
//    @Autowired
//    private WebtoonRepository webtoonRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @AfterEach
//    void tearDown() {
//        reviewRepository.deleteAll();
//        reviewLikeRepository.deleteAll();
//        webtoonRepository.deleteAll();
//        userRepository.deleteAll();
//    }
//
//
//    @DisplayName("?????? ?????? ?????????")
//    @Test
//    public void updateReview() {
//
//        //?????? ??????
//        User user = User.builder()
//            .userName("?????????")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //??????
//        Webtoon w1 = createWebtoon(20);
//        webtoonRepository.save(w1);
//
//        //?????? ??????
//        Review review1 = createReview("?????? ??????1", 4.5F, 13, user, w1);
//        Review review2 = createReview("?????? ??????2", 4.3F, 15, user, w1);
//
//        reviewRepository.save(review1);
//        reviewRepository.save(review2);
//
//        //then
//        ReviewContentRequestDto reviewDto = new ReviewContentRequestDto("?????? ?????? ??????1");
//        ReviewContentRequestDto reviewDto2 = new ReviewContentRequestDto("?????? ?????? ??????2");
//
//        reviewService.updateReview(review1.getId(), reviewDto, user);
//        reviewService.updateReview(review2.getId(), reviewDto2, user);
//
//        //when
//        assertThat(review1.getReviewContent()).isEqualTo(reviewDto.getReviewContent());
//        assertThat(review2.getReviewContent()).isEqualTo(reviewDto2.getReviewContent());
//
//    }
//
//    @DisplayName("????????? ????????????.")
//    @Test
//    public void deleteReview() {
//        //given
//
//        //?????? ??????
//        User user = User.builder()
//            .userName("?????????")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //??????
//        Webtoon w1 = createWebtoon(20);
//        webtoonRepository.save(w1);
//
//        //?????? ??????
//        Review review1 = createReview("?????? ??????1", 4.5F, 13, user, w1);
//        review1.insertWebToonAndUser(w1,user);
//
//        reviewRepository.save(review1);
//
//        //when
//        reviewService.deleteReview(review1.getId(), user);
//
//        //then
//        assertThat(reviewRepository.findById(review1.getId()).get().getReviewContent()).isNull();
//
//    }
//
//    /**
//     * ????????? ????????? ????????? ?????????
//     */
//    @DisplayName("????????? ???????????? ????????? ?????????_?????? ????????? ?????????")
//    @Test
//    public void clickReviewLike() {
//
//        //given
//        //?????? ??????
//        User user = User.builder()
//            .userName("?????????")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //??????
//        Webtoon w1 = createWebtoon(20);
//        webtoonRepository.save(w1);
//
//        //?????? ??????
//        Review review1 = createReview("?????? ??????1", 4.5F, 13, user, w1);
//        reviewRepository.save(review1);
//
//        ReviewLike reviewLike = ReviewLike.builder()
//            .user(user)
//            .review(review1)
//            .reviewStatus(ReviewLikeStatus.CANCEL)
//            .build();
//
//        reviewLikeRepository.save(reviewLike);
//
//        //when
//        reviewService.clickReviewLike(review1.getId(), user);
//
//        //then
//        assertThat(review1.getLikeCount()).isEqualTo(14);
//        assertThat(
//            reviewLikeRepository.findReviewLikeByReviewAndUser(review1, user).getReviewStatus())
//            .isEqualTo(ReviewLikeStatus.LIKE);
//
//    }
//
//    @DisplayName("????????? ???????????? ????????? ?????????(?????????->??????)")
//    @Test
//    public void clickReviewLike2() {
//
//        //given
//        ///?????? ??????
//        User user = User.builder()
//            .userName("?????????")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //??????
//        Webtoon w1 = createWebtoon(20);
//        webtoonRepository.save(w1);
//
//        //?????? ??????
//        Review review1 = createReview("?????? ??????1", 4.5F, 13, user, w1);
//        reviewRepository.save(review1);
//
//        ReviewLike reviewLike = ReviewLike.builder()
//            .user(user)
//            .review(review1)
//            .reviewStatus(ReviewLikeStatus.LIKE)
//            .build();
//
//        reviewLikeRepository.save(reviewLike);
//
//        reviewService.clickReviewLike(review1.getId(), user);
//
//        assertThat(reviewLike.getReviewStatus()).isEqualTo(ReviewLikeStatus.CANCEL);
//
//    }
//
//    @DisplayName("????????? ???????????? ????????? ?????????(??????->?????????)")
//    @Test
//    public void clickReviewLike3() {
//
//        //given
//        //?????? ??????
//        User user = User.builder()
//            .userName("?????????")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //??????
//        Webtoon w1 = createWebtoon(20);
//        webtoonRepository.save(w1);
//
//        //?????? ??????
//        Review review1 = createReview("?????? ??????1", 4.5F, 13, user, w1);
//        reviewRepository.save(review1);
//
//        ReviewLike reviewLike = ReviewLike.builder()
//            .user(user)
//            .review(review1)
//            .reviewStatus(ReviewLikeStatus.CANCEL)
//            .build();
//
//        reviewLikeRepository.save(reviewLike);
//
//        reviewService.clickReviewLike(review1.getId(), user);
//
//        assertThat(reviewLike.getReviewStatus()).isEqualTo(ReviewLikeStatus.LIKE);
//
//    }
//
//    /**
//     * ????????? ?????? ?????? ?????????
//     */
//    @DisplayName("????????? ?????? ?????? ?????????")
//    @Test
//    public void clickWebtoonStar1() {
//        //given
//        //?????? ??????
//        User user = User.builder()
//            .userName("?????????")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //?????? ??????
//        Webtoon w1 = createWebtoon(25);
//        webtoonRepository.save(w1);
//
//        //?????? ??????
//        WebtoonPointRequestDto webtoonPointRequestDto = new WebtoonPointRequestDto(w1.getId(),
//            1.0F);
//
//        //when
//        reviewService.clickWebtoonPointNumber(webtoonPointRequestDto, user);
//
//        //then
//        assertThat(w1.getToonAvgPoint()).isEqualTo(3.4F);
//        assertThat(w1.getTotalPointCount()).isEqualTo(26);
//
//        //when2 - ?????? ??????????????? ?????????
//        //?????? ??????2
//        WebtoonPointRequestDto webtoonPointRequestDto2 = new WebtoonPointRequestDto(w1.getId(),
//            2.0F);
//
//        reviewService.clickWebtoonPointNumber(webtoonPointRequestDto2, user);
//
//        assertThat(w1.getToonAvgPoint()).isEqualTo(3.43F);
//        assertThat(w1.getTotalPointCount()).isEqualTo(26);
//
//    }
//
//    @DisplayName("?????? ?????????/????????? ??????")
//    @Test
//    public void test() {
//        //given
//        //?????? ??????
//        User user = User.builder()
//            .userName("?????????")
//            .userImg(1)
//            .build();
//
//        userRepository.save(user);
//
//        //?????? ??????
//        Webtoon w1 = createWebtoon(25);
//        webtoonRepository.save(w1);
//
//        Review review1 = createReview("?????? ??????1", 1.5f, 1, user, w1);
//        Review review2 = createReview("?????? ??????2", 2.0f, 2, user, w1);
//        Review review3 = createReview("?????? ??????3", 2.5f, 3, user, w1);
//        Review review4 = createReview("?????? ??????4", 3.0f, 4, user, w1);
//        Review review5 = createReview("?????? ??????5", 3.5f, 5, user, w1);
//        Review review6 = createReview("?????? ??????6", 4.0f, 6, user, w1);
//        Review review7 = createReview("?????? ??????7", 4.5F, 7, user, w1);
//        Review review8 = createReview("?????? ??????8", 5.0f, 8, user, w1);
//
//        reviewRepository.save(review1);
//        reviewRepository.save(review2);
//        reviewRepository.save(review3);
//        reviewRepository.save(review4);
//        reviewRepository.save(review5);
//        reviewRepository.save(review6);
//        reviewRepository.save(review7);
//        reviewRepository.save(review8);
//
//        //when
//        List<ReviewResponseDto> reviewBestResponseDto = reviewService.getMainReview()
//            .getBestReview();
//        List<ReviewResponseDto> reviewResponseDto = reviewService.getMainReview().getNewReview();
//        ReviewMainResponseDto reviewMainResponseDto = reviewService.getMainReview();
//        //then
//        for (ReviewResponseDto responseDto : reviewBestResponseDto) {
//            System.out
//                .println("responseDto.getUserPointNumber()= " + responseDto.getUserPointNumber());
//        }
//        for (ReviewResponseDto responseDto1 : reviewResponseDto) {
//            System.out.println("responseDto1.getCreateDate()= " + responseDto1.getCreateDate());
//        }
//        ReviewMainResponseDto responseDto2 = new ReviewMainResponseDto(reviewBestResponseDto,
//            reviewResponseDto);
//        System.out.println("responseDto2.getBestReview()= " + responseDto2.getBestReview() + ", " +
//            "responseDto2.getNewReview" + responseDto2.getNewReview());
//
//    }
//
//    private Review createReview(String reviewContent, float userPointNumber, int likeCount,
//        User user, Webtoon webtoon) {
//        return Review.builder()
//            .reviewContent(reviewContent)
//            .userPointNumber(userPointNumber)
//            .likeCount(likeCount)
//            .user(user)
//            .webtoon(webtoon)
//            .build();
//    }
//
//    private Webtoon createWebtoon(int totalPointCount) {
//        return Webtoon.builder()
//            .toonTitle("??????1")
//            .toonAuthor("??????1")
//            .toonContent("??????1")
//            .toonImg("?????????.png")
//            .realUrl("http://naver")
//            .toonPlatform("?????????")
//            .toonAvgPoint((float) 3.5)
//            .totalPointCount(totalPointCount)
//            .build();
//    }
//
//}
//
