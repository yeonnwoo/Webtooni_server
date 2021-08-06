package com.webtooni.webtooniverse.domain.webtoon.service;


import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.MonthRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankResponseDto;
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
    WebtoonRepository webtoonRepository;
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

    @BeforeEach
    void getTestData(){
        Webtoon webtoon1 = new Webtoon("웹툰1", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 2.5f, 0, 4, false);
        Webtoon webtoon2 = new Webtoon("웹툰2", "작가1", "내용2", "이미지2", "화", "url", "15", "네이버", 3.5f, 0, 3, false);
        Webtoon webtoon3 = new Webtoon("웹툰3", "작가2", "내용3", "이미지3", "수", "url", "15", "네이버", 4.5f, 0, 2, false);
        Webtoon webtoon4 = new Webtoon("웹툰4", "작가3", "내용4", "이미지4", "목", "url", "15", "네이버", 5.0f, 0, 1, false);
        Webtoon webtoon5 = new Webtoon("웹툰5", "작가4", "내용5", "이미지5", "금", "url", "15", "네이버", 3.7f, 0, 5, false);
        Webtoon webtoon6 = new Webtoon("웹툰6", "작가5", "내용6", "이미지6", "월", "url", "15", "네이버", 2.1f, 0, 7, false);
        Webtoon webtoon7 = new Webtoon("웹툰7", "작가1", "내용7", "이미지7", "화", "url", "15", "네이버", 4.9f, 0, 1, false);
        Webtoon webtoon8 = new Webtoon("웹툰8", "작가2", "내용8", "이미지8", "토", "url", "15", "네이버", 4.8f, 0, 5, false);
        Webtoon webtoon9 = new Webtoon("웹툰9", "작가3", "내용9", "이미지9", "일", "url", "15", "네이버", 4.3f, 0, 7, false);
        Webtoon webtoon10 = new Webtoon("웹툰10", "작가4", "내용10", "이미지10", "월", "url", "15", "네이버", 4.6f, 0, 7, false);
        Webtoon webtoon11 = new Webtoon("웹툰11", "작가1", "내용1", "이미지1", "월", "url", "15", "카카오", 2.6f, 0, 4, false);
        Webtoon webtoon12 = new Webtoon("웹툰12", "작가1", "내용2", "이미지2", "화", "url", "15", "카카오", 3.6f, 0, 3, false);
        Webtoon webtoon13 = new Webtoon("웹툰13", "작가2", "내용3", "이미지3", "수", "url", "15", "카카오", 4.8f, 0, 2, false);
        Webtoon webtoon14 = new Webtoon("웹툰14", "작가3", "내용4", "이미지4", "목", "url", "15", "카카오", 4.2f, 0, 1, false);
        Webtoon webtoon15 = new Webtoon("웹툰15", "작가4", "내용5", "이미지5", "금", "url", "15", "카카오", 3.4f, 0, 5, false);
        Webtoon webtoon16 = new Webtoon("웹툰16", "작가5", "내용6", "이미지6", "월", "url", "15", "카카오", 1.9f, 0, 7, false);
        Webtoon webtoon17 = new Webtoon("웹툰17", "작가1", "내용7", "이미지7", "화", "url", "15", "카카오", 4.4f, 0, 1, false);
        Webtoon webtoon18 = new Webtoon("웹툰18", "작가2", "내용8", "이미지8", "토", "url", "15", "카카오", 4.7f, 0, 5, false);
        Webtoon webtoon19 = new Webtoon("웹툰19", "작가3", "내용9", "이미지9", "일", "url", "15", "카카오", 4.9f, 0, 7, false);
        Webtoon webtoon20 = new Webtoon("웹툰20", "작가4", "내용10", "이미지10", "월", "url", "15", "카카오", 4.3f, 0, 7, false);
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

    @DisplayName("웹투니버스 종합 랭킹")
    @Test
    public void test1(){
        //given

        //when
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

}