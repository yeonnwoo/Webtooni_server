package com.webtooni.webtooniverse.domain.review.service;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.review.dto.request.ReviewContentRequestDto;
import com.webtooni.webtooniverse.domain.review.dto.request.WebtoonPointRequestDto;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeRepository;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeStatus;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGrade;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UnitReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewLikeRepository reviewLikeRepository;

    @Mock
    private WebtoonRepository webtoonRepository;

    @InjectMocks
    private ReviewService reviewService;

    /**
     * 리뷰 수정 테스트
     */
    @DisplayName("리뷰 수정 테스트")
    @Test
    public void test1() {
        //given
        Review review1 = createReview(13);

        ReviewContentRequestDto requestDto = new ReviewContentRequestDto("변경될 내용");

        given(reviewRepository.findById(1L)).willReturn(Optional.of(review1));

        //when
        reviewService.updateReview(1L, requestDto);

        //then
        assertThat(review1.getReviewContent()).isEqualTo(requestDto.getReviewContent());
    }


    /**
     * 리뷰 삭제 테스트
     */

    @DisplayName("리뷰 삭제 테스트")
    @Test
    public void test2(){
        //given
        Review review1 = createReview(13);

        given(reviewRepository.findById(any())).willReturn(Optional.of(review1));

        //when
        reviewService.deleteReview(any());

        //then
        assertThat(review1.getReviewContent()).isNull();
    }

    /**
     * 리뷰에 좋아요 테스트
     * - 처음 좋아요
     * - 좋아요 -> 취소
     * - 취소 -> 좋아요
     */

    @DisplayName("처음 누르는 사용자_리뷰에 좋아요")
    @Test
    public void test3(){
        //given
        User user = User.builder()
                .userName("홍길동")
                .userEmail("abc@naver.com")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
                .build();

        Review review1 = createReview(0);

        given(reviewRepository.findById(any())).willReturn(Optional.of(review1));
        given(reviewLikeRepository.findReviewLikeByReviewAndUser(review1,user)).willReturn(null);

        //when
        reviewService.clickReviewLike(any(),user);

        //then
        assertThat(review1.getLikeCount()).isEqualTo(1);

    }

    @DisplayName("좋아요->취소_리뷰에 좋아요")
    @Test
    public void test4(){
        //given
        User user = User.builder()
                .userName("홍길동")
                .userEmail("abc@naver.com")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
                .build();

        Review review1 = createReview(1);

        ReviewLike reviewLike= ReviewLike.builder()
                .reviewStatus(ReviewLikeStatus.LIKE)
                .build();


        given(reviewRepository.findById(any())).willReturn(Optional.of(review1));
        given(reviewLikeRepository.findReviewLikeByReviewAndUser(review1,user)).willReturn(reviewLike);

        //when
        reviewService.clickReviewLike(any(),user);

        //then
        assertThat(review1.getLikeCount()).isEqualTo(0);
        assertThat(reviewLike.getReviewStatus()).isEqualTo(ReviewLikeStatus.CANCEL);

    }
    @DisplayName("취소->좋아요_리뷰에 좋아요")
    @Test
    public void test5(){
        //given
        User user = User.builder()
                .userName("홍길동")
                .userEmail("abc@naver.com")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
                .build();

        Review review1 = createReview(0);

        ReviewLike reviewLike= ReviewLike.builder()
                .reviewStatus(ReviewLikeStatus.CANCEL)
                .build();

        given(reviewRepository.findById(any())).willReturn(Optional.of(review1));
        given(reviewLikeRepository.findReviewLikeByReviewAndUser(review1,user)).willReturn(reviewLike);

        //when
        reviewService.clickReviewLike(any(),user);

        //then
        assertThat(review1.getLikeCount()).isEqualTo(1);
        assertThat(reviewLike.getReviewStatus()).isEqualTo(ReviewLikeStatus.LIKE);

    }

    /**
     * 웹툰에 별점 테스트
     */
    @DisplayName("웹툰에 별점 준적 없는 사용자")
    @Test
    public void getWebtoonPoint(){
        //given
        User user = User.builder()
                .userName("홍길동")
                .userEmail("abc@naver.com")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
                .build();

        Webtoon w1 = createWebtoon();

        WebtoonPointRequestDto webtoonPointRequestDto = new WebtoonPointRequestDto(1L, 1.0F);

        given(webtoonRepository.findById(any())).willReturn(Optional.of(w1));
        given(reviewRepository.checkUserPointIsExist(w1,user)).willReturn(null);

        //when
        reviewService.clickWebtoonPointNumber(webtoonPointRequestDto,user);

        //then
        assertThat(w1.getTotalPointCount()).isEqualTo(26);
        assertThat(w1.getToonAvgPoint()).isEqualTo(3.4F);

    }

    @DisplayName("웹툰에 별점 준적 있는 사용자")
    @Test
    public void getWebtoonPoint2(){
        //given
        User user = User.builder()
                .userName("홍길동")
                .userEmail("abc@naver.com")
                .userImg(1)
                .userGrade(UserGrade.FIRST)
                .build();

        Webtoon w1 = createWebtoon();

        WebtoonPointRequestDto webtoonPointRequestDto = new WebtoonPointRequestDto(1L, 1.0F);

        Review review = createReview(0);

        given(webtoonRepository.findById(any())).willReturn(Optional.of(w1));
        given(reviewRepository.checkUserPointIsExist(w1,user)).willReturn(review);

        //when
        reviewService.clickWebtoonPointNumber(webtoonPointRequestDto,user);

        //then
        assertThat(w1.getTotalPointCount()).isEqualTo(25);
        assertThat(w1.getToonAvgPoint()).isEqualTo(3.4F);
    }



    private Review createReview(int likeCount) {
        return Review.builder()
                .reviewContent("리뷰 내용1")
                .userPointNumber((float) 4.5)
                .likeCount(likeCount)
                .build();
    }

    private Webtoon createWebtoon() {
        return Webtoon.builder()
                .toonTitle("제목1")
                .toonAuthor("작가1")
                .toonContent("내용1")
                .toonAvgPoint((float) 3.5)
                .totalPointCount(25)
                .build();
    }

}
