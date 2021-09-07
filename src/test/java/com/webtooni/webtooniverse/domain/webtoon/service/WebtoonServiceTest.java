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
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
import com.webtooni.webtooniverse.domain.webtoonGenre.WebtoonGenre;
import com.webtooni.webtooniverse.domain.webtoonGenre.WebtoonGenreRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    EntityManager em;


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

    @Test
    public void br() {
//        List<Review> reviews = webtoonRepository.br(1L);

//        for (Long userId : data.keySet()) {
//            float sum = 0;
//            int cont
//            List<Float> movieAndScore = data.get(userId);
//        }
//        List<Long> idList = new ArrayList<>();
//
//        for (Review review : reviews) {
//            idList.add(review.getUser().getId());
//        }
//
//        List<List<Float>> outerList = new ArrayList<>();
//        for (Review review : reviews) {
//            List<Float> innerList = new ArrayList<>();
//            innerList.add((float) review.getUser().getId());
//            innerList.add((float) review.getWebtoon().getId());
//            innerList.add(review.getUserPointNumber());
//            outerList.add(innerList);
//        }
//
//        for (List<Float> floats : outerList) {
//            System.out.println(floats.get(0) + " / " + floats.get(1) + " / " + floats.get(2));
//            System.out.println("-----------------------------");
//        }

//
//        //한 row씩 돌고 있음
//        for (List<Float> innerList : outerList) {
//            for (Long userId : idList) {
//
//            }
//            float sum = 0;
//            int count = 0;
////            for (Float score : innerList) {
////                sum += score;
////                count += 1;
////            }
////            float avgScore = sum / count;
////            for (Float score : innerList) {
////                score -= avgScore;
////            }
//
//        }
//
//        for (List<Float> floats : outerList) {
//            System.out.println(floats.get(0) + " / " + floats.get(1) + " / " + floats.get(2));
//        }
//
//        <Long, List<Map<Long, Float>>> userWebtoonScore = new HashMap<>();
//        for (Review review : reviews) {
//            Long userId = review.getUser().getId();
//            Map<Long, Float> webtoonScore = new HashMap<>();
//            webtoonScore.put(review.getWebtoon().getId(), review.getUserPointNumber());
//            if (userWebtoonScore.containsKey(userId)) {
//                List<Map<Long, Float>> list = userWebtoonScore.get(userId);
//                list.add(webtoonScore);
//            } else {
//                List<Map<Long, Float>> list = new ArrayList<>();
//                list.add(webtoonScore);
//                userWebtoonScore.put(userId, list);
//            }
//        }
//
//        List<Long> webtoonIdList = new ArrayList<>();
//        List<Map<Long, Float>> mapList = userWebtoonScore.get(1L);
//        for (Map<Long, Float> longFloatMap : mapList) {
//            for(Long key : longFloatMap.keySet()){
//                webtoonIdList.add(key);
//            }
//        }
//        for (Long aLong : webtoonIdList) {
//            System.out.println("aLong = " + aLong);
//        }
//
//        for (Long key : userWebtoonScore.keySet()) {
//            //유저 한명
//            List<Map<Long, Float>> webtoonScoreList = userWebtoonScore.get(key);
//            for (Map<Long, Float> longFloatMap : webtoonScoreList) {
//                longFloatMap.values();
//                for( va)
//            }M
//
//            }
//
//        }
    }


    @DisplayName("웹툰 1개 정보,리뷰 리스트 불러오기 테스트")
    @Test
    public void webtoonDetailAndReviewList() {

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
        Webtoon w1 = createWebtoon("제목1", "작가1", "내용1", 20, 5.0f, "네이버");
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

    @DisplayName("웹투니버스 종합 랭킹")
    @Test
    public void test1() {
        //given

        //when
        List<WebtoonAndGenreResponseDto> totalRankToon = webtoonService.getMonthTotalRank();

        //then
        for (WebtoonAndGenreResponseDto rankResponseDto : totalRankToon) {
            System.out.println(
                "rankResponseDto.getToonTitle()=" + rankResponseDto.getToonTitle() + ", " +
                    "rankResponseDto.getToonAvgPoint()=" + rankResponseDto.getToonAvgPoint());
        }

    }

    @DisplayName("웹투니버스 네이버 랭킹")
    @Test
    public void test2() {
        //given
        //웹툰 저장
        Webtoon w1 = createWebtoon("제목1", "작가1", "내용1", 20, 3.0f, "네이버");
        Webtoon w2 = createWebtoon("제목2", "작가2", "내용2", 20, 5.0f, "네이버");
        Webtoon w3 = createWebtoon("제목3", "작가3", "내용3", 20, 4.5f, "네이버");
        Webtoon w4 = createWebtoon("제목4", "작가4", "내용4", 20, 4.0f, "네이버");
        Webtoon w5 = createWebtoon("제목5", "작가5", "내용5", 20, 3.5f, "네이버");
        Webtoon w6 = createWebtoon("제목6", "작가6", "내용6", 20, 5.0f, "카카오");
        Webtoon w7 = createWebtoon("제목7", "작가7", "내용7", 20, 4.5f, "카카오");
        Webtoon w8 = createWebtoon("제목8", "작가8", "내용8", 20, 4.0f, "카카오");
        Webtoon w9 = createWebtoon("제목9", "작가9", "내용9", 20, 3.5f, "카카오");
        Webtoon w10 = createWebtoon("제목10", "작가10", "내용10", 20, 3.0f, "카카오");

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
        List<PlatformRankResponseDto> platformRankToon = webtoonService.getMonthKakaoRank();

        //then
        for (PlatformRankResponseDto platformRankResponseDto : platformRankToon) {
            System.out.println(
                "rankResponseDto.getToonTitle()=" + platformRankResponseDto.getToonTitle() + ", " +
                    "rankResponseDto.getToonAvgPoint()=" + platformRankResponseDto.getToonAvgPoint()
                    + ", " +
                    "rankResponseDto.getToonPlatform()= "
                    + platformRankResponseDto.getToonPlatform());
        }

    }

    @DisplayName("웹투니버스 카카오 랭킹")
    @Test
    public void test3() {
        //given

        //when
        List<PlatformRankResponseDto> platformRankToon = webtoonService.getMonthKakaoRank();
        //then
        for (PlatformRankResponseDto platformRankResponseDto : platformRankToon) {
            System.out.println(
                "rankResponseDto.getToonTitle()=" + platformRankResponseDto.getToonTitle() + ", " +
                    "rankResponseDto.getToonAvgPoint()=" + platformRankResponseDto.getToonAvgPoint()
                    + ", " +
                    "rankResponseDto.getToonPlatform()= "
                    + platformRankResponseDto.getToonPlatform());
        }

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
    private Webtoon createWebtoon(String toonTitle, String toonAuthor, String toonContent,
        int totalPointCount, float toonAvgPointCount, String toonPlatform) {
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
