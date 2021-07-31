package com.webtooni.webtooniverse.domain.webtoon.service;

import com.webtooni.webtooniverse.domain.Genre.domain.Genre;
import com.webtooni.webtooniverse.domain.Genre.domain.GenreRepository;
import com.webtooni.webtooniverse.domain.WebtoonGenre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewDto;
import com.webtooni.webtooniverse.domain.review.service.ReviewService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
import org.junit.jupiter.api.AfterEach;
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
class WebtoonServiceTest {

    @Autowired
    private WebtoonService webtoonService;

    @PersistenceContext
    EntityManager em;

    @DisplayName("웹툰 1개 정보,리뷰 리스트 불러오기 테스트")
    @Test
    public void 웹툰정보와_리뷰리스트를_보내준다() throws Exception{

        //given

        //임시 장르 저장
        Genre g1 = createGenre("일상");
        Genre g2 = createGenre("개그");
        Genre g3 = createGenre("판타지");

        em.persist(g1);
        em.persist(g2);
        em.persist(g3);

        //웹툰 생성
        Webtoon w1 = createWebtoon("제목1", "작가1", "내용1");
        em.persist(w1);
        em.flush();


        //웹툰_장르 설정
        WebtoonGenre wg1 = createWebToonGenre(w1, g1);
        WebtoonGenre wg2 = createWebToonGenre(w1, g2);

        em.persist(wg1);
        em.persist(wg2);


        //리뷰 생성
        Review review1 = createReview("리뷰 내용1", 4.5F, 13);
        Review review2 = createReview("리뷰 내용2", 4.3F, 15);

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

        //when
        WebtoonDetailDto webtoonDetailDto = webtoonService.getDetailAndReviewList(w1.getId());

        //then

        //웹툰 기본정보
        assertThat(webtoonDetailDto.getToonContent()).isEqualTo(w1.getToonContent());
        assertThat(webtoonDetailDto.getToonTitle()).isEqualTo(w1.getToonTitle());
        assertThat(webtoonDetailDto.getToonAuthor()).isEqualTo(w1.getToonAuthor());

        //웹툰 장르정보
        assertThat(webtoonDetailDto.getToonGenre().size()).isEqualTo(2);
        assertThat(webtoonDetailDto.getToonGenre().get(0).getGenreType()).isEqualTo("일상");
        assertThat(webtoonDetailDto.getToonGenre().get(1).getGenreType()).isEqualTo("개그");

        //리뷰리스트 정보
        assertThat(webtoonDetailDto.getReviews().size()).isEqualTo(2);
        assertThat(webtoonDetailDto.getReviews().get(0).getReviewContent()).isEqualTo(review1.getReviewContent());
        assertThat(webtoonDetailDto.getReviews().get(0).getUserPointNumber()).isEqualTo(review1.getUserPointNumber());
        assertThat(webtoonDetailDto.getReviews().get(0).getLikeCount()).isEqualTo(review1.getLikeCount());

        assertThat(webtoonDetailDto.getReviews().get(1).getReviewContent()).isEqualTo(review2.getReviewContent());
        assertThat(webtoonDetailDto.getReviews().get(1).getUserPointNumber()).isEqualTo(review2.getUserPointNumber());
        assertThat(webtoonDetailDto.getReviews().get(1).getLikeCount()).isEqualTo(review2.getLikeCount());

    }

    /**
     * 데이터를 임의로 생성한다.
     */

    //리뷰 생성
    private Review createReview(String reviewContent, float userPointNumber, int likeCount) {
        return Review.builder()
                .reviewContent(reviewContent)
                .userPointNumber(userPointNumber)
                .likeCount(likeCount)
                .build();
    }

    //웹툰 생성
    private Webtoon createWebtoon(String title, String author, String content) {
        return Webtoon.builder()
                .toonTitle(title)
                .toonAuthor(author)
                .toonContent(content)
                .build();
    }

    //장르 생성
    private Genre createGenre(String type) {
        return Genre.builder()
                .genreType(type)
                .build();
    }

    //웹툰 장르 연관관계 설정
    private WebtoonGenre createWebToonGenre(Webtoon webtoon, Genre genre) {
        return WebtoonGenre.builder()
                .webtoon(webtoon)
                .genre(genre)
                .build();
    }

}