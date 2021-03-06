//package com.webtooni.webtooniverse.domain.webtoon.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//
//import com.webtooni.webtooniverse.domain.genre.domain.Genre;
//import com.webtooni.webtooniverse.domain.myList.MyListRepository;
//import com.webtooni.webtooniverse.domain.review.domain.Review;
//import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
//import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
//import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeRepository;
//import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeStatus;
//import com.webtooni.webtooniverse.domain.user.domain.User;
//import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
//import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
//import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
//import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
//import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.util.ReflectionTestUtils;
//
//@ExtendWith(MockitoExtension.class)
//public class UnitWebtoonServiceTest {
//
//    @InjectMocks
//    private WebtoonService webtoonService;
//
//    @Mock
//    private WebtoonRepository webtoonRepository;
//
//    @Mock
//    private ReviewRepository reviewRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private ReviewLikeRepository reviewLikeRepository;
//
//    @Mock
//    private MyListRepository myListRepository;
//
//    /**
//     * 웹툰 1개 정보, 리뷰 리스트 불러오기 테스트
//     */
//    @DisplayName("웹툰 정보,리뷰 리스트 불러오기 테스트")
//    @Test
//    public void test1() {
//        //given
//        //장르
//        Genre g1 = createGenre("일상");
//        Genre g2 = createGenre("개그");
//
//        List<Genre> genres = new ArrayList<>(Arrays.asList(g1, g2));
//
//        //임시 유저
//        User user = User.builder()
//            .userName("홍길동")
//            .userImg(1)
//            .build();
//
//        Long fakeId = 123L;
//        ReflectionTestUtils.setField(user, "id", fakeId);
//
//        userRepository.save(user);
//
//        //웹툰
//        Webtoon w1 = createWebtoon();
//
//        webtoonRepository.save(w1);
//
//        //리뷰
//        Review review1 = createReview("리뷰 내용1", 4.5F, 13, user, w1);
//        Review review2 = createReview("리뷰 내용2", 4.3F, 15, user, w1);
//
//        reviewRepository.save(review1);
//        reviewRepository.save(review2);
//
//        //리뷰좋아요
//        ReviewLike reviewLike = ReviewLike.builder()
//            .user(user)
//            .review(review1)
//            .reviewStatus(ReviewLikeStatus.LIKE)
//            .build();
//
//        reviewLikeRepository.save(reviewLike);
//
//        List<Review> reviews = new ArrayList<>(Arrays.asList(review1, review2));
//        List<Long> reviewId = new ArrayList<>();
//        reviewId.add(review1.getId());
//
//        //mocking
//        given(webtoonRepository.findById(1L)).willReturn(Optional.of(w1));
//        given(webtoonRepository.findWebToonGenre(w1)).willReturn(genres);
//        given(reviewRepository.findReviewByWebToonId(1L)).willReturn(reviews);
//        given(reviewLikeRepository.findReviewIdListByUser(fakeId)).willReturn(reviewId);
//        given(myListRepository.existsById(fakeId, 1L)).willReturn(true);
//
//        UserDetailsImpl userDetails = new UserDetailsImpl(user);
//
//        //when
//        WebtoonDetailDto detailAndReviewList = webtoonService
//            .getDetailAndReviewList(1L, userDetails);
//
//        //then
//        assertThat(detailAndReviewList.getReviews().size()).isEqualTo(2);
//        assertThat(detailAndReviewList.getGenres().get(0)).isEqualTo("일상");
//        assertThat(detailAndReviewList.getGenres().get(1)).isEqualTo("개그");
//
//    }
//
//    /**
//     * 데이터를 임의로 생성한다.
//     */
//
//    private Review createReview(String reviewContent, float userPointNumber, int likeCount,
//        User user, Webtoon webtoon) {
//        return Review.builder()
//            .reviewContent(reviewContent)
//            .userPointNumber(userPointNumber)
//            .likeCount(likeCount)
//            .user(user)
//            .webtoon(webtoon)
//            .build();
//    }
//
//    private Webtoon createWebtoon() {
//        return Webtoon.builder()
//            .toonTitle("제목1")
//            .toonAuthor("작가1")
//            .toonContent("내용1")
//            .toonImg("이미지.png")
//            .realUrl("http://naver")
//            .toonPlatform("네이버")
//            .toonAvgPoint((float) 3.5)
//            .totalPointCount(20)
//            .build();
//    }
//
//    //장르 생성
//    private Genre createGenre(String type) {
//        return Genre.builder()
//            .genreType(type)
//            .build();
//    }
//
//}
