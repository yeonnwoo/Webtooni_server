package com.webtooni.webtooniverse.domain.webtoon.service;


import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.genre.domain.GenreRepository;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.MonthRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
import com.webtooni.webtooniverse.domain.webtoonGenre.WebtoonGenre;
import com.webtooni.webtooniverse.domain.webtoonGenre.WebtoonGenreRepository;
import org.junit.jupiter.api.AfterEach;
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

    @PersistenceContext
    EntityManager em;



    @AfterEach
    void tearDown()
    {
        webtoonRepository.deleteAll();
        reviewRepository.deleteAll();
        genreRepository.deleteAll();
        webtoonGenreRepository.deleteAll();
        userRepository.deleteAll();
    }

    @DisplayName("웹툰 1개 정보,리뷰 리스트 불러오기 테스트")
    @Test
    public void webtoonDetailAndReviewList(){

        //given
        //임시 유저
        User user = User.builder()
                .userName("홍길동")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
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
        Webtoon w1 = createWebtoon("제목1", "작가1", "내용1",20);
        webtoonRepository.save(w1);


        //웹툰_장르 설정
        WebtoonGenre wg1 = createWebToonGenre(w1, g1);
        WebtoonGenre wg2 = createWebToonGenre(w1, g2);

        webtoonGenreRepository.save(wg1);
        webtoonGenreRepository.save(wg2);


        //리뷰 생성
        Review review1 = createReview("리뷰 내용1", 4.5F, 13,user,w1);
        Review review2 = createReview("리뷰 내용2", 4.3F, 15,user,w1);

        review1.insertWebToonAndUser(w1, user);
        review2.insertWebToonAndUser(w1, user);

        reviewRepository.save(review1);
        reviewRepository.save(review2);

        //when
        WebtoonDetailDto webtoonDetailDto = webtoonService.getDetailAndReviewList(w1.getId(),user);

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

    @DisplayName("웹투니버스 종합 랭킹")
    @Test
    public void test1(){
        //given
        //장르 저장
        Genre g1 = createGenre("일상");
        Genre g2 = createGenre("개그");
        Genre g3 = createGenre("판타지");

        genreRepository.save(g1);
        genreRepository.save(g2);
        genreRepository.save(g3);


        //웹툰 저장
        Webtoon w1 = createWebtoon("제목1", "작가1", "내용1",20);
        Webtoon w2 = createWebtoon("제목2", "작가2", "내용2",20);
        Webtoon w3 = createWebtoon("제목3", "작가3", "내용3",20);
        Webtoon w4 = createWebtoon("제목4", "작가4", "내용4",20);
        Webtoon w5 = createWebtoon("제목5", "작가5", "내용5",20);
        Webtoon w6 = createWebtoon("제목6", "작가6", "내용6",20);
        Webtoon w7 = createWebtoon("제목7", "작가7", "내용7",20);
        Webtoon w8 = createWebtoon("제목8", "작가8", "내용8",20);
        Webtoon w9 = createWebtoon("제목9", "작가9", "내용9",20);
        Webtoon w10 = createWebtoon("제목10", "작가10", "내용10",20);


        webtoonRepository.save(w1);
        webtoonRepository.save(w2);
        webtoonRepository.save(w3);
        webtoonRepository.save(w4);
        webtoonRepository.save(w5);
        webtoonRepository.save(w6);
        webtoonRepository.save(w7);
        webtoonRepository.save(w8);
        webtoonRepository.save(w9);
        webtoonRepository.save(w10);


        //when
        //웹툰_장르 설정
        WebtoonGenre wg1 = createWebToonGenre(w1, g1);
        WebtoonGenre wg2 = createWebToonGenre(w2, g2);
        WebtoonGenre wg3 = createWebToonGenre(w3, g3);
        WebtoonGenre wg4 = createWebToonGenre(w4, g1);
        WebtoonGenre wg5 = createWebToonGenre(w5, g2);
        WebtoonGenre wg6 = createWebToonGenre(w6, g3);
        WebtoonGenre wg7 = createWebToonGenre(w7, g1);
        WebtoonGenre wg8 = createWebToonGenre(w8, g2);
        WebtoonGenre wg9 = createWebToonGenre(w9, g3);
        WebtoonGenre wg10 = createWebToonGenre(w10, g1);

        webtoonGenreRepository.save(wg1);
        webtoonGenreRepository.save(wg2);
        webtoonGenreRepository.save(wg3);
        webtoonGenreRepository.save(wg4);
        webtoonGenreRepository.save(wg5);
        webtoonGenreRepository.save(wg6);
        webtoonGenreRepository.save(wg7);
        webtoonGenreRepository.save(wg8);
        webtoonGenreRepository.save(wg9);
        webtoonGenreRepository.save(wg10);

        List<MonthRankResponseDto> totalRankToon = webtoonService.getMonthTotalRank();

        //then
        for(MonthRankResponseDto rankResponseDto : totalRankToon){
            System.out.println("rankResponseDto.getToonTitle()=" + rankResponseDto.getToonTitle()+ ", "+
                    "rankResponseDto.getToonAvgPoint()=" + rankResponseDto.getToonAvgPoint());
        }
        assertThat(totalRankToon.size()).isEqualTo(10);
    }

    @DisplayName("웹투니버스 네이버 랭킹")
    @Test
    public void test2(){
        //given

        //when
        List<PlatformRankResponseDto> platformRankToon = webtoonService.getMonthNaverRank();
        //then
        for(PlatformRankResponseDto platformRankResponseDto : platformRankToon){
            System.out.println("rankResponseDto.getToonTitle()=" + platformRankResponseDto.getToonTitle() +", "+
                    "rankResponseDto.getToonAvgPoint()=" + platformRankResponseDto.getToonAvgPoint() +", "+
                    "rankResponseDto.getToonPlatform()= " + platformRankResponseDto.getToonPlatform());
        }
        assertThat(platformRankToon.size()).isEqualTo(10);
    }

    @DisplayName("웹투니버스 카카오 랭킹")
    @Test
    public void test3(){
        //given

        //when
        List<PlatformRankResponseDto> platformRankToon = webtoonService.getMonthKakaoRank();
        //then
        for(PlatformRankResponseDto platformRankResponseDto : platformRankToon){
            System.out.println("rankResponseDto.getToonTitle()=" + platformRankResponseDto.getToonTitle() +", "+
                    "rankResponseDto.getToonAvgPoint()=" + platformRankResponseDto.getToonAvgPoint() +", "+
                    "rankResponseDto.getToonPlatform()= " + platformRankResponseDto.getToonPlatform());
        }
        assertThat(platformRankToon.size()).isEqualTo(10);
    }

    /**
     * 데이터를 임의로 생성한다.
     */

    //리뷰 생성
    private Review createReview(String reviewContent, float userPointNumber, int likeCount,User user,Webtoon webtoon) {
        return Review.builder()
                .reviewContent(reviewContent)
                .userPointNumber(userPointNumber)
                .likeCount(likeCount)
                .user(user)
                .webtoon(webtoon)
                .build();
    }

    //웹툰 생성
    private Webtoon createWebtoon(String toonTitle,String toonAuthor,String toonContent,int totalPointCount) {
        return Webtoon.builder()
                .toonTitle(toonTitle)
                .toonAuthor(toonAuthor)
                .toonContent(toonContent)
                .toonImg("이미지.png")
                .realUrl("http://naver")
                .toonPlatform("네이버")
                .toonAvgPoint((float) 3.5)
                .totalPointCount(totalPointCount)
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
