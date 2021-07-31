package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.webtooni.webtooniverse.domain.Genre.domain.Genre;
import com.webtooni.webtooniverse.domain.Genre.domain.GenreRepository;
import com.webtooni.webtooniverse.domain.WebtoonGenre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
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
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@Rollback(value = false)
@SpringBootTest
class WebtoonRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private WebtoonRepository webtoonRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("웹툰의 장르가 잘 찾아와지는지 테스트")
    public void test() throws Exception {
        //given

        //장르 저장
        Genre g1 = createGenre("일상");
        Genre g2 = createGenre("개그");
        Genre g3 = createGenre("판타지");

        genreRepository.save(g1);
        genreRepository.save(g2);
        genreRepository.save(g3);


        //웹툰 저장
        Webtoon w1 = createWebtoon("제목1", "작가1", "내용1");
        Webtoon w2 = createWebtoon("제목2", "작가2", "내용2");
        webtoonRepository.save(w1);
        webtoonRepository.save(w2);


        //when
        //연관관계 설정
        WebtoonGenre webtoonGenre1 = new WebtoonGenre();
        WebtoonGenre wg1 = webtoonGenre1.createWebToonGenre(g1, w1);
        WebtoonGenre wg2 = webtoonGenre1.createWebToonGenre(g2, w1);

        WebtoonGenre webtoonGenre2 = new WebtoonGenre();
        WebtoonGenre wg3 = webtoonGenre2.createWebToonGenre(g2, w2);
        WebtoonGenre wg4 = webtoonGenre2.createWebToonGenre(g3, w2);

        em.persist(wg1);
        em.persist(wg2);
        em.persist(wg3);
        em.persist(wg4);


        //then
        List<Genre> webToonGenre = webtoonRepository.findWebToonGenre(w1);
        assertThat(webToonGenre.get(0).getGenreType()).isEqualTo("일상");
        assertThat(webToonGenre.get(1).getGenreType()).isEqualTo("개그");

        List<Genre> webToonGenre2 = webtoonRepository.findWebToonGenre(w2);
        assertThat(webToonGenre2.get(0).getGenreType()).isEqualTo("개그");
        assertThat(webToonGenre2.get(1).getGenreType()).isEqualTo("판타지");

    }

    @Test
    @DisplayName("웹툰의 Review 가져오기")
    public void test2() throws Exception{
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

        //웹툰
        Webtoon w1 = createWebtoon("제목1", "작가1", "내용1");
        Webtoon w2 = createWebtoon("제목2", "작가2", "내용2");
        webtoonRepository.save(w1);
        webtoonRepository.save(w2);


        //유저
        User user = User.builder()
                .userName("홍길동")
                .userEmail("abc@naver.com")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
                .build();

        em.persist(user);

        review1.insertWebToonAndUser(w1,user);
        review2.insertWebToonAndUser(w1,user);

        //when
        reviewRepository.save(review1);
        reviewRepository.save(review2);

        //then
        List<Review> reviewList = webtoonRepository.findReviewByWebToonId(w1.getId());

        assertThat(reviewList.get(0).getReviewContent()).isEqualTo(review1.getReviewContent());
        assertThat(reviewList.get(1).getReviewContent()).isEqualTo(review2.getReviewContent());
    }


    /**
     * webtoon,Genre 데이터를 임의로 생성한다.
     */
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