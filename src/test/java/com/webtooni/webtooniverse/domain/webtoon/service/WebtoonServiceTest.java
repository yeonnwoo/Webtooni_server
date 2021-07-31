package com.webtooni.webtooniverse.domain.webtoon.service;

import com.webtooni.webtooniverse.domain.Genre.domain.Genre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewDto;
import com.webtooni.webtooniverse.domain.review.service.ReviewService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@Rollback(value = false)
@Transactional
@SpringBootTest
class WebtoonServiceTest {

    @Autowired
    private WebtoonService webtoonService;

    @Autowired
    private ReviewService reviewService;

    @PersistenceContext
    EntityManager em;


    @DisplayName("웹툰 상세 정보와 리뷰 리스트 반환")
    @Test
    public void test() throws Exception{
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


        //유저
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

        ReviewDto reviewDto = new ReviewDto("바뀐 리뷰 내용1");
        ReviewDto reviewDto2 = new ReviewDto("바뀐 리뷰 내용2");

        //when : review update
        reviewService.updateReview(review1.getId(),reviewDto);
        reviewService.updateReview(review2.getId(),reviewDto2);

    }

    private Webtoon createWebtoon(String title, String author, String content) {
        return Webtoon.builder()
                .toonTitle(title)
                .toonAuthor(author)
                .toonContent(content)
                .build();
    }

    private Genre createGenre(String type) {
        return Genre.builder()
                .genreType(type)
                .build();
    }

}