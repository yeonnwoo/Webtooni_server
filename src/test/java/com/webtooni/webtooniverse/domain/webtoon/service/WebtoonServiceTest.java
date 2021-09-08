package com.webtooni.webtooniverse.domain.webtoon.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.genre.domain.GenreRepository;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
import com.webtooni.webtooniverse.domain.webtoonGenre.WebtoonGenre;
import com.webtooni.webtooniverse.domain.webtoonGenre.WebtoonGenreRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Transactional
@SpringBootTest
class WebtoonServiceTest {

    @Autowired
    private WebtoonRepository webtoonRepository;

    @Autowired
    private WebtoonService webtoonService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private WebtoonGenreRepository webtoonGenreRepository;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void getTestData() {
        Webtoon webtoon1 = new Webtoon("웹툰1", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 4.0f,
            0, 4, false);
        Webtoon webtoon2 = new Webtoon("웹툰2", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 4.0f,
            0, 4, false);
        Webtoon webtoon3 = new Webtoon("웹툰3", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 4.0f,
            0, 4, false);
        Webtoon webtoon4 = new Webtoon("웹툰4", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 13.0f,
            0, 4, true);
        Webtoon webtoon5 = new Webtoon("웹툰5", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 15.0f,
            0, 4, true);
        Webtoon webtoon6 = new Webtoon("웹툰6", "작가1", "내용1", "이미지1", "월", "url", "15", "카카오", 14.5f,
            0, 4, true);
        Webtoon webtoon7 = new Webtoon("웹툰7", "작가1", "내용1", "이미지1", "월", "url", "15", "카카오", 5.0f,
            0, 4, true);
        Webtoon webtoon8 = new Webtoon("웹툰8", "작가1", "내용1", "이미지1", "월", "url", "15", "카카오", 5.5f,
            0, 4, true);
        Webtoon webtoon9 = new Webtoon("웹툰9", "작가1", "내용1", "이미지1", "월", "url", "15", "카카오", 6.0f,
            0, 4, true);
        Webtoon webtoon10 = new Webtoon("웹툰10", "작가1", "내용1", "이미지1", "월", "url", "15", "카카오", 7.0f,
            0, 4, true);

        webtoonRepository.save(webtoon1);
        webtoonRepository.save(webtoon2);
        webtoonRepository.save(webtoon3);
        webtoonRepository.save(webtoon4);
        webtoonRepository.save(webtoon5);
        webtoonRepository.save(webtoon6);
        webtoonRepository.save(webtoon7);
        webtoonRepository.save(webtoon8);
        webtoonRepository.save(webtoon9);
        webtoonRepository.save(webtoon10);

        Genre genre1 = new Genre("코믹");
        Genre genre2 = new Genre("멜로");
        Genre genre3 = new Genre("액션");
        genreRepository.save(genre1);
        genreRepository.save(genre2);
        genreRepository.save(genre3);

        WebtoonGenre webtoonGenre1 = new WebtoonGenre(genre1, webtoon1);
        WebtoonGenre webtoonGenre2 = new WebtoonGenre(genre2, webtoon2);
        WebtoonGenre webtoonGenre3 = new WebtoonGenre(genre3, webtoon3);
        WebtoonGenre webtoonGenre4 = new WebtoonGenre(genre1, webtoon4);
        WebtoonGenre webtoonGenre5 = new WebtoonGenre(genre2, webtoon5);
        WebtoonGenre webtoonGenre6 = new WebtoonGenre(genre3, webtoon6);
        WebtoonGenre webtoonGenre7 = new WebtoonGenre(genre1, webtoon7);
        WebtoonGenre webtoonGenre8 = new WebtoonGenre(genre2, webtoon8);
        WebtoonGenre webtoonGenre9 = new WebtoonGenre(genre3, webtoon9);
        WebtoonGenre webtoonGenre10 = new WebtoonGenre(genre1, webtoon10);
        webtoonGenreRepository.save(webtoonGenre1);
        webtoonGenreRepository.save(webtoonGenre2);
        webtoonGenreRepository.save(webtoonGenre3);
        webtoonGenreRepository.save(webtoonGenre4);
        webtoonGenreRepository.save(webtoonGenre5);
        webtoonGenreRepository.save(webtoonGenre6);
        webtoonGenreRepository.save(webtoonGenre7);
        webtoonGenreRepository.save(webtoonGenre8);
        webtoonGenreRepository.save(webtoonGenre9);
        webtoonGenreRepository.save(webtoonGenre10);
    }

    @AfterEach
    void tearDown() {
        webtoonRepository.deleteAll();
        reviewRepository.deleteAll();
        genreRepository.deleteAll();
        webtoonGenreRepository.deleteAll();
        userRepository.deleteAll();
    }


    @DisplayName("웹툰 1개 정보,리뷰 리스트 불러오기 테스트")
    @Test
    public void webtoonDetailAndReviewList() {

        //given
        //임시 유저
        User user = User.builder()
            .userName("홍길동")
            .userImg(1)
            .build();

        userRepository.save(user);

        //임시 장르 저장
        Genre g1 = createGenre("일상");
        Genre g2 = createGenre("개그");
        Genre g3 = createGenre("판타지");

        genreRepository.save(g1);
        genreRepository.save(g2);
        genreRepository.save(g3);

        //웹툰 생성
        Webtoon w1 = createWebtoon();
        webtoonRepository.save(w1);

        //웹툰_장르 설정
        WebtoonGenre wg1 = createWebToonGenre(w1, g1);
        WebtoonGenre wg2 = createWebToonGenre(w1, g2);

        webtoonGenreRepository.save(wg1);
        webtoonGenreRepository.save(wg2);

        //리뷰 생성
        Review review1 = createReview("리뷰 내용1", 4.5F, 13, user, w1);
        Review review2 = createReview("리뷰 내용2", 4.3F, 15, user, w1);

        review1.insertWebToonAndUser(w1, user);
        review2.insertWebToonAndUser(w1, user);

        reviewRepository.save(review1);
        reviewRepository.save(review2);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        //when
        WebtoonDetailDto webtoonDetailDto = webtoonService.getDetailAndReviewList(w1.getId(),
            userDetails);

        //then

        //웹툰 기본정보
        assertThat(webtoonDetailDto.getToonContent()).isEqualTo(w1.getToonContent());
        assertThat(webtoonDetailDto.getToonTitle()).isEqualTo(w1.getToonTitle());
        assertThat(webtoonDetailDto.getToonAuthor()).isEqualTo(w1.getToonAuthor());

        //웹툰 장르정보
        assertThat(webtoonDetailDto.getGenres().size()).isEqualTo(2);
        assertThat(webtoonDetailDto.getGenres().get(0)).isEqualTo("일상");
        assertThat(webtoonDetailDto.getGenres().get(1)).isEqualTo("개그");

        //리뷰리스트 정보
        assertThat(webtoonDetailDto.getReviews().size()).isEqualTo(2);
        assertThat(webtoonDetailDto.getReviews().get(0).getReviewContent()).isEqualTo(
            review1.getReviewContent());
        assertThat(webtoonDetailDto.getReviews().get(0).getUserPointNumber()).isEqualTo(
            review1.getUserPointNumber());
        assertThat(webtoonDetailDto.getReviews().get(0).getLikeCount()).isEqualTo(
            review1.getLikeCount());

        assertThat(webtoonDetailDto.getReviews().get(1).getReviewContent()).isEqualTo(
            review2.getReviewContent());
        assertThat(webtoonDetailDto.getReviews().get(1).getUserPointNumber()).isEqualTo(
            review2.getUserPointNumber());
        assertThat(webtoonDetailDto.getReviews().get(1).getLikeCount()).isEqualTo(
            review2.getLikeCount());


    }

    /**
     * 데이터를 임의로 생성한다.
     */

    //리뷰 생성
    private Review createReview(String reviewContent, float userPointNumber, int likeCount,
        User user, Webtoon webtoon) {
        return Review.builder()
            .reviewContent(reviewContent)
            .userPointNumber(userPointNumber)
            .likeCount(likeCount)
            .user(user)
            .webtoon(webtoon)
            .build();
    }

    //웹툰 생성
    private Webtoon createWebtoon() {
        return Webtoon.builder()
            .toonTitle("제목1")
            .toonAuthor("작가1")
            .toonContent("내용1")
            .toonImg("이미지.png")
            .realUrl("http://naver")
            .toonPlatform("네이버")
            .toonAvgPoint((float) 3.5)
            .totalPointCount(20)
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
