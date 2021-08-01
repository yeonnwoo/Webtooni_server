package com.webtooni.webtooniverse.domain.review.service;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewContentRequestDto;
import com.webtooni.webtooniverse.domain.reviewLike.ReviewLikeRepository;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeStatus;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public void updateReview() throws Exception {

        //리뷰 생성
        Review review1 = createReview("리뷰 내용1", 4.5F, 13);
        Review review2 = createReview("리뷰 내용2", 4.3F, 15);

        //웹툰
        Webtoon w1 = createWebtoon("제목1", "작가1", "내용1");
        em.persist(w1);
        em.flush();

        //임시 유저
        User user = User.builder()
                .userName("홍길동")
                .userEmail("abc@naver.com")
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

    private Review createReview(String reviewContent, float userPointNumber, int likeCount) {
        return Review.builder()
                .reviewContent(reviewContent)
                .userPointNumber(userPointNumber)
                .likeCount(likeCount)
                .build();
    }

    private Webtoon createWebtoon(String title, String author, String content) {
        return Webtoon.builder()
                .toonTitle(title)
                .toonAuthor(author)
                .toonContent(content)
                .build();
    }

    @DisplayName("리뷰를 삭제한다.")
    @Test
    public void deleteReview() throws Exception {
        //given
        //리뷰 생성
        Review review1 = createReview("리뷰 내용1", 4.5F, 13);
        Review review2 = createReview("리뷰 내용2", 4.3F, 15);

        em.persist(review1);
        em.persist(review2);

        //when
        reviewService.deleteReview(review1.getId());

        //then
        assertThat(reviewRepository.findAll().size()).isEqualTo(1);

    }

    /**
     * 리뷰에 좋아요 누르기 테스트
     */

    @DisplayName("리뷰에 좋아요를 누르기 테스트_처음 누르는 사용자")
    @Test
    public void clickReviewLike() throws Exception{

        //given

        //임시 유저
        User user = User.builder()
                .userName("홍길동")
                .userEmail("abc@naver.com")
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
                .reviewStatus(ReviewLikeStatus.CANCLE)
                .build();

        em.persist(reviewLike);
        em.flush();

        //when
        reviewService.clickReviewLike(review1.getId(),user);

        //then
        assertThat(review1.getLikeCount()).isEqualTo(1);
        assertThat(reviewLikeRepository.findReviewLikeByReviewAndUser(review1,user).getReviewStatus()).isEqualTo(ReviewLikeStatus.LIKE);

    }

    @DisplayName("리뷰에 좋아요를 누르기 테스트(좋아요->취소)")
    @Test
    public void clickReviewLike2() throws Exception{

        //given

        //임시 유저
        User user = User.builder()
                .userName("홍길동")
                .userEmail("abc@naver.com")
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

        reviewService.clickReviewLike(review1.getId(),user);

        assertThat(reviewLike.getReviewStatus()).isEqualTo(ReviewLikeStatus.CANCLE);

    }

    @DisplayName("리뷰에 좋아요를 누르기 테스트(취소->좋아요)")
    @Test
    public void clickReviewLike3() throws Exception{

        //given

        //임시 유저
        User user = User.builder()
                .userName("홍길동")
                .userEmail("abc@naver.com")
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
                .reviewStatus(ReviewLikeStatus.CANCLE)
                .build();

        em.persist(reviewLike);
        em.flush();

        reviewService.clickReviewLike(review1.getId(),user);

        assertThat(reviewLike.getReviewStatus()).isEqualTo(ReviewLikeStatus.LIKE);

    }

}