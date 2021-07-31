package com.webtooni.webtooniverse.domain.review.service;

import com.webtooni.webtooniverse.domain.Genre.domain.Genre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewDto;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Rollback(value = false)
@Transactional
@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void setUp()
    {

    }

    @DisplayName("리뷰 수정 테스트")
    @Test
    public void 리뷰수정() throws Exception{
        //리뷰
        Review review1 = Review.builder()
                .reviewContent("리뷰 내용1")
                .userPointNumber(4.5F)
                .likeCount(13)
                .build();

        Review review2 = Review.builder()
                .reviewContent("리뷰 내용2")
                .userPointNumber(4.5F)
                .likeCount(13)
                .build();

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

        review1.insertWebToonAndUser(w1,user);
        review2.insertWebToonAndUser(w1,user);

        em.persist(review1);
        em.persist(review2);

        //then
        ReviewDto reviewDto = new ReviewDto("바뀐 리뷰 내용1");
        ReviewDto reviewDto2 = new ReviewDto("바뀐 리뷰 내용2");

        reviewService.updateReview(review1.getId(),reviewDto);
        reviewService.updateReview(review2.getId(),reviewDto2);

        //when
        assertThat(review1.getReviewContent()).isEqualTo(reviewDto.getReviewContent());
        assertThat(review2.getReviewContent()).isEqualTo(reviewDto2.getReviewContent());

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
    public void 리뷰삭제() throws Exception{
        //given
        //리뷰
        Review review1 = Review.builder()
                .reviewContent("리뷰 내용1")
                .userPointNumber(4.5F)
                .likeCount(13)
                .build();

        Review review2 = Review.builder()
                .reviewContent("리뷰 내용2")
                .userPointNumber(4.5F)
                .likeCount(13)
                .build();

        em.persist(review1);
        em.persist(review2);


        //when
        reviewService.deleteReview(review1.getId());

        //then
        assertThat(reviewRepository.findAll().size()).isEqualTo(1);

    }

}