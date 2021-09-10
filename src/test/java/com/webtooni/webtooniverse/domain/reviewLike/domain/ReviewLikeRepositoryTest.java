package com.webtooni.webtooniverse.domain.reviewLike.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class ReviewLikeRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    WebtoonRepository webtoonRepository;
    @Autowired
    ReviewLikeRepository reviewLikeRepository;

    @Test
    @DisplayName("해당 리뷰에 대해 사용자가 좋아요한 이력 조회")
    void findReviewLikeByReviewAndUser() {
        //given
        User user = User.builder().userName("유저1").build();
        User savedUser = userRepository.save(user);

        Webtoon webtoon = Webtoon.builder().toonTitle("웹툰1").realUrl("url1").toonAuthor("작가1")
            .toonContent("내용").toonImg("이미지").toonPlatform("플랫폼").build();
        Webtoon savedWebtoon = webtoonRepository.save(webtoon);

        Review review = Review.builder().user(user).webtoon(webtoon).reviewContent("리뷰1").build();
        Review savedReview = reviewRepository.save(review);

        ReviewLike reviewLike = ReviewLike.of(savedUser, savedReview);
        reviewLikeRepository.save(reviewLike);

        //when
        ReviewLike findReviewLike = reviewLikeRepository
            .findReviewLikeByReviewAndUser(review, user);

        //then
        assertThat(findReviewLike.getUser().getUserName())
            .isEqualTo("유저1");
        assertThat(findReviewLike.getReview().getReviewContent())
            .isEqualTo("리뷰1");
    }

    @Test
    @DisplayName("사용자가 좋아요한 전체 리뷰 조회")
    void findReviewIdListByUser() {
        //given
        User user = User.builder().userName("유저1").build();
        User savedUser = userRepository.save(user);

        Webtoon webtoon = Webtoon.builder().toonTitle("웹툰1").realUrl("url1").toonAuthor("작가1")
            .toonContent("내용").toonImg("이미지").toonPlatform("플랫폼").build();
        Webtoon savedWebtoon = webtoonRepository.save(webtoon);

        Review review1 = Review.builder().user(user).webtoon(webtoon).reviewContent("리뷰1").build();
        Review savedReview1 = reviewRepository.save(review1);

        Review review2 = Review.builder().user(user).webtoon(webtoon).reviewContent("리뷰2").build();
        Review savedReview2 = reviewRepository.save(review2);

        ReviewLike reviewLike1 = ReviewLike.of(savedUser, savedReview1);
        reviewLikeRepository.save(reviewLike1);
        ReviewLike reviewLike2 = ReviewLike.of(savedUser, savedReview2);
        reviewLikeRepository.save(reviewLike2);

        //when
        List<Long> reviewIdListByUser = reviewLikeRepository
            .findReviewIdListByUser(savedUser.getId());

        //then
        assertThat(reviewIdListByUser.size()).isEqualTo(2);
    }
}