package com.webtooni.webtooniverse.domain.review.service;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;

import com.webtooni.webtooniverse.domain.review.dto.response.ReviewBestResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewMainResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewNewResponseDto;
import org.junit.jupiter.api.BeforeEach;

import com.webtooni.webtooniverse.domain.review.dto.request.ReviewContentRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.request.WebtoonPointRequestDto;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeRepository;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeStatus;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewLikeRepository reviewLikeRepository;

    @PersistenceContext
    EntityManager em;

    @DisplayName("리뷰 수정 테스트")
    @Test
    public void updateReview() {

        //리뷰 생성
        Review review1 = createReview("리뷰 내용1", 4.5F, 13);
        Review review2 = createReview("리뷰 내용2", 4.3F, 15);

        //웹툰
        Webtoon w1 = createWebtoon(20);
        em.persist(w1);
        em.flush();

        //임시 유저
        User user = User.builder()
                .userName("홍길동")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
                .build();

        em.persist(user);
        em.flush();

        review1.insertWebToonAndUser(w1, user);
        review2.insertWebToonAndUser(w1, user);

        em.persist(review1);
        em.persist(review2);

        //then
        ReviewContentRequestDto reviewDto = new ReviewContentRequestDto("바뀐 리뷰 내용1");
        ReviewContentRequestDto reviewDto2 = new ReviewContentRequestDto("바뀐 리뷰 내용2");

        reviewService.updateReview(review1.getId(), reviewDto);
        reviewService.updateReview(review2.getId(), reviewDto2);

        //when
        assertThat(review1.getReviewContent()).isEqualTo(reviewDto.getReviewContent());
        assertThat(review2.getReviewContent()).isEqualTo(reviewDto2.getReviewContent());

    }


    @DisplayName("리뷰를 삭제한다.")
    @Test
    public void deleteReview() {
        //given
        //리뷰 생성
        Review review1 = createReview("리뷰 내용1", 4.5F, 13);
        Review review2 = createReview("리뷰 내용2", 4.3F, 15);

        em.persist(review1);
        em.persist(review2);

        //when
        reviewService.deleteReview(review1.getId());

        //then
        assertThat(reviewRepository.findAll().size()).isEqualTo(2);
        assertThat(reviewRepository.findById(review1.getId()).get().getReviewContent()).isNull();

    }

    /**
     * 리뷰에 좋아요 누르기 테스트
     */

    @DisplayName("리뷰에 좋아요를 누르기 테스트_처음 누르는 사용자")
    @Test
    public void clickReviewLike() {

        //given

        //임시 유저
        User user = User.builder()
                .userName("홍길동")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
                .build();

        em.persist(user);
        em.flush();

        //리뷰 생성
        Review review1 = createReview("리뷰 내용1", 4.5F, 0);
        em.persist(review1);
        em.flush();

        ReviewLike reviewLike = ReviewLike.builder()
                .user(user)
                .review(review1)
                .reviewStatus(ReviewLikeStatus.CANCEL)
                .build();

        em.persist(reviewLike);
        em.flush();

        //when
        reviewService.clickReviewLike(review1.getId(), user);

        //then
        assertThat(review1.getLikeCount()).isEqualTo(1);
        assertThat(reviewLikeRepository.findReviewLikeByReviewAndUser(review1, user).getReviewStatus()).isEqualTo(ReviewLikeStatus.LIKE);

    }

    @DisplayName("리뷰에 좋아요를 누르기 테스트(좋아요->취소)")
    @Test
    public void clickReviewLike2() {

        //given

        //임시 유저
        User user = User.builder()
                .userName("홍길동")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
                .build();

        em.persist(user);

        //리뷰 생성
        Review review1 = createReview("리뷰 내용1", 4.5F, 0);
        em.persist(review1);


        ReviewLike reviewLike = ReviewLike.builder()
                .user(user)
                .review(review1)
                .reviewStatus(ReviewLikeStatus.LIKE)
                .build();

        em.persist(reviewLike);

        em.flush();

        reviewService.clickReviewLike(review1.getId(), user);

        assertThat(reviewLike.getReviewStatus()).isEqualTo(ReviewLikeStatus.CANCEL);

    }

    @DisplayName("리뷰에 좋아요를 누르기 테스트(취소->좋아요)")
    @Test
    public void clickReviewLike3() {

        //given

        //임시 유저
        User user = User.builder()
                .userName("홍길동")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
                .build();

        em.persist(user);
        em.flush();

        //리뷰 생성
        Review review1 = createReview("리뷰 내용1", 4.5F, 0);
        em.persist(review1);

        ReviewLike reviewLike = ReviewLike.builder()
                .user(user)
                .review(review1)
                .reviewStatus(ReviewLikeStatus.CANCEL)
                .build();

        em.persist(reviewLike);
        em.flush();

        reviewService.clickReviewLike(review1.getId(), user);

        assertThat(reviewLike.getReviewStatus()).isEqualTo(ReviewLikeStatus.LIKE);

    }

    /**
     * 웹툰에 별점 주기 테스트
     */
    @DisplayName("웹툰에 별점 주기 테스트")
    @Test
    public void clickWebtoonStar1() {
        //given
        //임시 유저
        User user = User.builder()
                .userName("홍길동")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
                .build();

        em.persist(user);
        em.flush();

        //웹툰 생성
        Webtoon w1 = createWebtoon(25);
        em.persist(w1);
        em.flush();

        //별점 정보
        WebtoonPointRequestDto webtoonPointRequestDto = new WebtoonPointRequestDto(w1.getId(), 1.0F);

        //when
        reviewService.clickWebtoonPointNumber(webtoonPointRequestDto, user);


        //then
        assertThat(w1.getToonAvgPoint()).isEqualTo(3.4F);
        assertThat(w1.getTotalPointCount()).isEqualTo(26);

        //when2 - 별점 수정하려는 사용자
        //별점 정보2
        WebtoonPointRequestDto webtoonPointRequestDto2 = new WebtoonPointRequestDto(w1.getId(), 2.0F);

        reviewService.clickWebtoonPointNumber(webtoonPointRequestDto2, user);

        assertThat(w1.getToonAvgPoint()).isEqualTo(3.4F);
        assertThat(w1.getTotalPointCount()).isEqualTo(26);

    }


    private Review createReview(String reviewContent, float userPointNumber, int likeCount) {
        return Review.builder()
                .reviewContent(reviewContent)
                .userPointNumber(userPointNumber)
                .likeCount(likeCount)
                .build();
    }

    private Webtoon createWebtoon(int totalPointCount) {
        return Webtoon.builder()
                .toonTitle("제목1")
                .toonAuthor("작가1")
                .toonContent("내용1")
                .toonAvgPoint((float) 3.5)
                .totalPointCount(totalPointCount)
                .build();
    }

    @BeforeEach
    void getTestData(){
        Review review1 = new Review("테스트내용1", 1.5f, 1);
        Review review2 = new Review("테스트내용2", 2.0f, 2);
        Review review3 = new Review("테스트내용3", 2.5f, 3);
        Review review4 = new Review("테스트내용4", 3.0f, 4);
        Review review5 = new Review("테스트내용5", 3.5f, 5);
        Review review6 = new Review("테스트내용6", 4.0f, 6);
        Review review7 = new Review("테스트내용7", 4.5f, 7);
        Review review8 = new Review("테스트내용8", 5.0f, 8);
        reviewRepository.save(review1);
        reviewRepository.save(review2);
        reviewRepository.save(review3);
        reviewRepository.save(review4);
        reviewRepository.save(review5);
        reviewRepository.save(review6);
        reviewRepository.save(review7);
        reviewRepository.save(review8);
    }

    @DisplayName("리뷰 베스트/최신순 조회")
    @Test
    public void test(){
        //given

        //when
        List<ReviewBestResponseDto> reviewBestResponseDto = reviewService.getMainReview().getBestReview();
        List<ReviewNewResponseDto> reviewNewResponseDto = reviewService.getMainReview().getNewReview();
        ReviewMainResponseDto reviewMainResponseDto = reviewService.getMainReview();
        //then
        for (ReviewBestResponseDto responseDto : reviewBestResponseDto){
            System.out.println("responseDto.getUserPointNumber()= " + responseDto.getUserPointNumber());
        }
        for (ReviewNewResponseDto responseDto1 : reviewNewResponseDto){
            System.out.println("responseDto1.getCreateDate()= " + responseDto1.getCreateDate());
        }
        ReviewMainResponseDto responseDto2 = new ReviewMainResponseDto(reviewBestResponseDto, reviewNewResponseDto);
        System.out.println("responseDto2.getBestReview()= " + responseDto2.getBestReview()+", " +
                "responseDto2.getNewReview" + responseDto2.getNewReview());



    }
}