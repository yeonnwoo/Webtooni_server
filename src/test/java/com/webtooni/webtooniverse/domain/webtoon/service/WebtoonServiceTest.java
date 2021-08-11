package com.webtooni.webtooniverse.domain.webtoon.service;


import com.webtooni.webtooniverse.domain.genre.domain.GenreRepository;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.MonthRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoonGenre.WebtoonGenreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.SimilarGenreToonDto;
import com.webtooni.webtooniverse.domain.webtoonGenre.WebtoonGenre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;

import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.transaction.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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

    @BeforeEach
    void getTestData(){
        Webtoon webtoon1 = new Webtoon(1L, "제목1", "작가1", "설명1", "imgUrl", "월", "realUrl", "전체", "네이버", 4.5f, 1, 1, false);
        Webtoon webtoon2 = new Webtoon(2L, "제목2", "작가2", "설명2", "imgUrl", "화", "realUrl", "전체", "네이버", 2.5f, 2, 2, false);
        Webtoon webtoon3 = new Webtoon(3L, "제목3", "작가3", "설명3", "imgUrl", "수", "realUrl", "전체", "네이버", 3.0f, 3, 3, false);
        Webtoon webtoon4 = new Webtoon(4L, "제목4", "작가4", "설명4", "imgUrl", "목", "realUrl", "전체", "네이버", 3.5f, 4, 4, false);
        Webtoon webtoon5 = new Webtoon(5L, "제목5", "작가5", "설명5", "imgUrl", "금", "realUrl", "전체", "네이버", 4.0f, 5, 5, false);
        Webtoon webtoon6 = new Webtoon(6L, "제목6", "작가6", "설명6", "imgUrl", "토", "realUrl", "전체", "네이버", 4.5f, 6, 6, false);
        Webtoon webtoon7 = new Webtoon(7L, "제목7", "작가7", "설명7", "imgUrl", "일", "realUrl", "전체", "네이버", 5.0f, 7, 7, false);
        Webtoon webtoon8 = new Webtoon(8L, "제목8", "작가8", "설명8", "imgUrl", "월", "realUrl", "전체", "네이버", 4.0f, 8, 8, false);
        Webtoon webtoon9 = new Webtoon(9L, "제목9", "작가9", "설명9", "imgUrl", "화", "realUrl", "전체", "네이버", 2.0f, 9, 9, false);
        Webtoon webtoon10 = new Webtoon(10L, "제목10", "작가10", "설명10", "imgUrl", "수", "realUrl", "전체", "네이버", 4.1f, 10, 10, false);
        Webtoon webtoon11 = new Webtoon(11L, "제목11", "작가11", "설명11", "imgUrl", "목", "realUrl", "전체", "카카오", 4.2f, 11, 11, false);
        Webtoon webtoon12 = new Webtoon(12L, "제목12", "작가12", "설명12", "imgUrl", "금", "realUrl", "전체", "카카오", 4.3f, 12, 12, false);
        Webtoon webtoon13 = new Webtoon(20L, "제목20", "작가20", "설명20", "imgUrl", "금", "realUrl", "전체", "카카오", 1.7f, 20, 21, false);
        Webtoon webtoon14 = new Webtoon(13L, "제목13", "작가13", "설명13", "imgUrl", "토", "realUrl", "전체", "카카오", 4.4f, 12, 13, false);
        Webtoon webtoon15 = new Webtoon(14L, "제목14", "작가14", "설명14", "imgUrl", "일", "realUrl", "전체", "카카오", 4.5f, 13, 14, false);
        Webtoon webtoon16 = new Webtoon(15L, "제목15", "작가15", "설명15", "imgUrl", "월", "realUrl", "전체", "카카오", 4.6f, 14, 15, false);
        Webtoon webtoon17 = new Webtoon(16L, "제목16", "작가16", "설명16", "imgUrl", "화", "realUrl", "전체", "카카오", 4.7f, 15, 16, false);
        Webtoon webtoon18 = new Webtoon(17L, "제목17", "작가17", "설명17", "imgUrl", "수", "realUrl", "전체", "카카오", 4.8f, 16, 17, false);
        Webtoon webtoon19 = new Webtoon(18L, "제목18", "작가18", "설명18", "imgUrl", "목", "realUrl", "전체", "카카오", 4.9f, 17, 18, false);
        Webtoon webtoon20 = new Webtoon(19L, "제목19", "작가19", "설명19", "imgUrl", "금", "realUrl", "전체", "카카오", 1.5f, 18, 19, false);
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
        webtoonRepository.save(webtoon11);
        webtoonRepository.save(webtoon12);
        webtoonRepository.save(webtoon13);
        webtoonRepository.save(webtoon14);
        webtoonRepository.save(webtoon15);
        webtoonRepository.save(webtoon16);
        webtoonRepository.save(webtoon17);
        webtoonRepository.save(webtoon18);
        webtoonRepository.save(webtoon19);
        webtoonRepository.save(webtoon20);
    }

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

    @DisplayName("웹투니버스 종합 랭킹")
    @Test
    public void test1(){
//        //given
//
//        //when
//        List<MonthRankResponseDto> totalRankToon = webtoonService.getMonthTotalRank();
//        //then
//        for(MonthRankResponseDto rankResponseDto : totalRankToon){
//            System.out.println("rankResponseDto.getToonTitle()=" + rankResponseDto.getToonTitle()+ ", "+
//                    "rankResponseDto.getToonAvgPoint()=" + rankResponseDto.getToonAvgPoint());
//        }
//        assertThat(totalRankToon.size()).isEqualTo(10);
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
