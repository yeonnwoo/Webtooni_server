//<<<<<<< HEAD
////package com.webtooni.webtooniverse.domain.user.service;
////
////import com.webtooni.webtooniverse.domain.review.domain.Review;
////import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
////import com.webtooni.webtooniverse.domain.user.domain.User;
////import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
////import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
////import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
////import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
////import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
////import org.junit.jupiter.api.AfterEach;
////import org.junit.jupiter.api.BeforeEach;
////import org.junit.jupiter.api.DisplayName;
////import org.junit.jupiter.api.Test;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.test.context.SpringBootTest;
////
////import javax.transaction.Transactional;
////import java.util.List;
////
////@SpringBootTest
////@Transactional
////class UserServiceTest {
////
////    @Autowired
////    UserRepository userRepository;
////    @Autowired
////    UserService userService;
////    @Autowired
////    WebtoonRepository webtoonRepository;
////    @Autowired
////    ReviewRepository reviewRepository;
////
////    @BeforeEach
////    void getTestData(){
////        Webtoon webtoon1 = new Webtoon("웹툰1", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 2.5f, 0, 4, false);
////        Webtoon webtoon2 = new Webtoon("웹툰2", "작가1", "내용2", "이미지2", "화", "url", "15", "네이버", 3.5f, 0, 3, false);
////        Webtoon webtoon3 = new Webtoon("웹툰3", "작가2", "내용3", "이미지3", "수", "url", "15", "네이버", 4.5f, 0, 2, false);
////        Webtoon webtoon4 = new Webtoon("웹툰4", "작가3", "내용4", "이미지4", "목", "url", "15", "네이버", 5.0f, 0, 1, false);
////        Webtoon webtoon5 = new Webtoon("웹툰5", "작가4", "내용5", "이미지5", "금", "url", "15", "네이버", 3.7f, 0, 5, false);
////        Webtoon webtoon6 = new Webtoon("웹툰6", "작가5", "내용6", "이미지6", "월", "url", "15", "네이버", 2.1f, 0, 7, false);
////        Webtoon webtoon7 = new Webtoon("웹툰7", "작가1", "내용7", "이미지7", "화", "url", "15", "네이버", 4.9f, 0, 1, false);
////        Webtoon webtoon8 = new Webtoon("웹툰8", "작가2", "내용8", "이미지8", "토", "url", "15", "네이버", 4.8f, 0, 5, false);
////        Webtoon webtoon9 = new Webtoon("웹툰9", "작가3", "내용9", "이미지9", "일", "url", "15", "네이버", 4.3f, 0, 7, false);
////        Webtoon webtoon10 = new Webtoon("웹툰10", "작가4", "내용10", "이미지10", "월", "url", "15", "카카오", 4.6f, 0, 7, false);
////        webtoonRepository.save(webtoon1);
////        webtoonRepository.save(webtoon2);
////        webtoonRepository.save(webtoon3);
////        webtoonRepository.save(webtoon4);
////        webtoonRepository.save(webtoon5);
////        webtoonRepository.save(webtoon6);
////        webtoonRepository.save(webtoon7);
////        webtoonRepository.save(webtoon8);
////        webtoonRepository.save(webtoon9);
////        webtoonRepository.save(webtoon10);
////        User user1 = new User("user1",  1, UserGrade.FIRST);
////        User user2 = new User("user2", 2, UserGrade.FIRST);
////        User user3 = new User("user3",  3, UserGrade.FIRST);
////        User user4 = new User("user4", 4, UserGrade.FIRST);
////        userRepository.save(user1);
////        userRepository.save(user2);
////        userRepository.save(user3);
////        userRepository.save(user4);
////        Review review1 = new Review("테스트내용1", 1.5f, 1, webtoon1, user1);
////        Review review2 = new Review("테스트내용2", 2.0f, 1, webtoon2, user2);
////        Review review3 = new Review("테스트내용3", 2.5f, 1, webtoon3, user2);
////        Review review4 = new Review("테스트내용4", 3.5f, 1, webtoon4, user2);
////        Review review5 = new Review("테스트내용5", 4.5f, 1, webtoon5, user2);
////        Review review6 = new Review("테스트내용6", 5.0f, 1, webtoon6, user2);
////        Review review7 = new Review("테스트내용7", 4.5f, 1, webtoon7, user3);
////        Review review8 = new Review("테스트내용8", 3.5f, 1, webtoon8, user3);
////        reviewRepository.save(review1);
////        reviewRepository.save(review2);
////        reviewRepository.save(review3);
////        reviewRepository.save(review4);
////        reviewRepository.save(review5);
////        reviewRepository.save(review6);
////        reviewRepository.save(review7);
////        reviewRepository.save(review8);
////
////    }
////
////    @AfterEach
////    void tearDown() {
////        userRepository.deleteAll();
////        webtoonRepository.deleteAll();
////        reviewRepository.deleteAll();
////    }
////
////    @DisplayName("베스트 리뷰어(리뷰작성개수 순)")
////    @Test
////    public void test(){
////        //given
////
////        //when
////        List<BestReviewerResponseDto> BestReviewer = userService.getBestReviewerRank();
////        //then
////        for (BestReviewerResponseDto bestReviewerResponseDto : BestReviewer) {
////            System.out.println("bestReviewerResponseDto.getUserName()" + bestReviewerResponseDto.getUser());
////        }
////    }
////}
//=======
//package com.webtooni.webtooniverse.domain.user.service;
//
//import com.webtooni.webtooniverse.domain.review.domain.Review;
//import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
//import com.webtooni.webtooniverse.domain.user.domain.User;
//import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
//import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
//import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
//import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
//import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//class UserServiceTest {
//
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    UserService userService;
//    @Autowired
//    WebtoonRepository webtoonRepository;
//    @Autowired
//    ReviewRepository reviewRepository;
//
//    @BeforeEach
//    void getTestData(){
//        Webtoon webtoon1 = new Webtoon("웹툰1", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 2.5f, 0, 4, false);
//        Webtoon webtoon2 = new Webtoon("웹툰2", "작가1", "내용2", "이미지2", "화", "url", "15", "네이버", 3.5f, 0, 3, false);
//        Webtoon webtoon3 = new Webtoon("웹툰3", "작가2", "내용3", "이미지3", "수", "url", "15", "네이버", 4.5f, 0, 2, false);
//        Webtoon webtoon4 = new Webtoon("웹툰4", "작가3", "내용4", "이미지4", "목", "url", "15", "네이버", 5.0f, 0, 1, false);
//        Webtoon webtoon5 = new Webtoon("웹툰5", "작가4", "내용5", "이미지5", "금", "url", "15", "네이버", 3.7f, 0, 5, false);
//        Webtoon webtoon6 = new Webtoon("웹툰6", "작가5", "내용6", "이미지6", "월", "url", "15", "네이버", 2.1f, 0, 7, false);
//        Webtoon webtoon7 = new Webtoon("웹툰7", "작가1", "내용7", "이미지7", "화", "url", "15", "네이버", 4.9f, 0, 1, false);
//        Webtoon webtoon8 = new Webtoon("웹툰8", "작가2", "내용8", "이미지8", "토", "url", "15", "네이버", 4.8f, 0, 5, false);
//        Webtoon webtoon9 = new Webtoon("웹툰9", "작가3", "내용9", "이미지9", "일", "url", "15", "네이버", 4.3f, 0, 7, false);
//        Webtoon webtoon10 = new Webtoon("웹툰10", "작가4", "내용10", "이미지10", "월", "url", "15", "카카오", 4.6f, 0, 7, false);
//        webtoonRepository.save(webtoon1);
//        webtoonRepository.save(webtoon2);
//        webtoonRepository.save(webtoon3);
//        webtoonRepository.save(webtoon4);
//        webtoonRepository.save(webtoon5);
//        webtoonRepository.save(webtoon6);
//        webtoonRepository.save(webtoon7);
//        webtoonRepository.save(webtoon8);
//        webtoonRepository.save(webtoon9);
//        webtoonRepository.save(webtoon10);
//
//        User user1 = new User("user1",  1, UserGrade.FIRST);
//        User user2 = new User("user2", 2, UserGrade.FIRST);
//        User user3 = new User("user3",  3, UserGrade.FIRST);
//        User user4 = new User("user4", 4, UserGrade.FIRST);
//        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(user3);
//        userRepository.save(user4);
//        Review review1 = new Review("테스트내용1", 1.5f, 1, webtoon1, user1);
//        Review review2 = new Review("테스트내용2", 2.0f, 1, webtoon2, user2);
//        Review review3 = new Review("테스트내용3", 2.5f, 1, webtoon3, user2);
//        Review review4 = new Review("테스트내용4", 3.5f, 1, webtoon4, user2);
//        Review review5 = new Review("테스트내용5", 4.5f, 1, webtoon5, user2);
//        Review review6 = new Review("테스트내용6", 5.0f, 1, webtoon6, user2);
//        Review review7 = new Review("테스트내용7", 4.5f, 1, webtoon7, user3);
//        Review review8 = new Review("테스트내용8", 3.5f, 1, webtoon8, user3);
//        reviewRepository.save(review1);
//        reviewRepository.save(review2);
//        reviewRepository.save(review3);
//        reviewRepository.save(review4);
//        reviewRepository.save(review5);
//        reviewRepository.save(review6);
//        reviewRepository.save(review7);
//        reviewRepository.save(review8);
//
//    }
//
//    @AfterEach
//    void tearDown() {
//        userRepository.deleteAll();
//        webtoonRepository.deleteAll();
//        reviewRepository.deleteAll();
//    }
//
//    @DisplayName("베스트 리뷰어(리뷰작성개수 순)")
//    @Test
//    public void test(){
//        //given
//
//        //when
//        List<BestReviewerResponseDto> BestReviewer = userService.getBestReviewerRank();
//        //then
//        for (BestReviewerResponseDto bestReviewerResponseDto : BestReviewer) {
//            System.out.println("bestReviewerResponseDto.getUserName()" + bestReviewerResponseDto.getUser());
//        }
//    }
//}
//>>>>>>> 99f351911e6897dee3f20f66f491fbfaa26b4874
