package com.webtooni.webtooniverse.domain.webtoon.service;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.SimilarGenreToonDto;
import com.webtooni.webtooniverse.domain.webtoonGenre.WebtoonGenre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void webtoonDetailAndReviewList(){

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
        assertThat(webtoonDetailDto.getToonGenre().get(0)).isEqualTo("일상");
        assertThat(webtoonDetailDto.getToonGenre().get(1)).isEqualTo("개그");

        //리뷰리스트 정보
        assertThat(webtoonDetailDto.getReviews().size()).isEqualTo(2);
        assertThat(webtoonDetailDto.getReviews().get(0).getReviewContent()).isEqualTo(review1.getReviewContent());
        assertThat(webtoonDetailDto.getReviews().get(0).getUserPointNumber()).isEqualTo(review1.getUserPointNumber());
        assertThat(webtoonDetailDto.getReviews().get(0).getLikeCount()).isEqualTo(review1.getLikeCount());

        assertThat(webtoonDetailDto.getReviews().get(1).getReviewContent()).isEqualTo(review2.getReviewContent());
        assertThat(webtoonDetailDto.getReviews().get(1).getUserPointNumber()).isEqualTo(review2.getUserPointNumber());
        assertThat(webtoonDetailDto.getReviews().get(1).getLikeCount()).isEqualTo(review2.getLikeCount());


    }

    @DisplayName("비슷한 장르의 웹툰을 랜덤으로 추천 테스트")
    @Test
    public void test(){
        //given
        //웹툰
        Webtoon w1 = createWebtoon("제목1", "작가1", "내용1");
        Webtoon w2 = createWebtoon("제목2", "작가2", "내용2");
        Webtoon w3 = createWebtoon("제목3", "작가3", "내용3");
        Webtoon w4 = createWebtoon("제목4", "작가4", "내용4");
        Webtoon w5 = createWebtoon("제목5", "작가5", "내용5");
        em.persist(w1);
        em.persist(w2);
        em.persist(w3);
        em.persist(w4);
        em.persist(w5);

        //장르 저장
        Genre g1 = createGenre("일상");
        Genre g2 = createGenre("개그");
        Genre g3 = createGenre("판타지");

        em.persist(g1);
        em.persist(g2);
        em.persist(g3);

        //웹툰_장르 설정
        WebtoonGenre wg1 = createWebToonGenre(w1, g1);
        WebtoonGenre wg2 = createWebToonGenre(w1, g2);
        WebtoonGenre wg3 = createWebToonGenre(w2, g1);
        WebtoonGenre wg4 = createWebToonGenre(w2, g2);
        WebtoonGenre wg5 = createWebToonGenre(w3, g3);
        WebtoonGenre wg6 = createWebToonGenre(w3, g2);
        WebtoonGenre wg7 = createWebToonGenre(w4, g1);
        WebtoonGenre wg8 = createWebToonGenre(w4, g3);
        WebtoonGenre wg9 = createWebToonGenre(w5, g1);
        WebtoonGenre wg10 = createWebToonGenre(w5, g2);

        em.persist(wg1);
        em.persist(wg2);
        em.persist(wg3);
        em.persist(wg4);
        em.persist(wg5);
        em.persist(wg6);
        em.persist(wg7);
        em.persist(wg8);
        em.persist(wg9);
        em.persist(wg10);


        //when
        List<SimilarGenreToonDto> toonDtos = webtoonService.getSimilarGenre(w1.getId());

        //then
        assertThat(toonDtos.size()).isEqualTo(2);
        assertThat(toonDtos.get(0).getToonTitle()).isNotEqualTo(w1.getToonTitle());

        for (SimilarGenreToonDto toonDto : toonDtos) {
            System.out.println("toonDto.getToonTitle() = " + toonDto.getToonTitle());
        }

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