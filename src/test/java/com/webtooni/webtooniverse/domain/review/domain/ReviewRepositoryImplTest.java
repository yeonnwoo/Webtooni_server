package com.webtooni.webtooniverse.domain.review.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.webtooni.webtooniverse.domain.review.dto.response.ReviewResponseDto;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootTest
@Transactional
class ReviewRepositoryImplTest {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    WebtoonRepository webtoonRepository;

    @Test
    void getBestOrNewReview() {
        //given
        User user = User.builder().userName("유저1").build();
        userRepository.save(user);
        Webtoon webtoon = Webtoon.builder().toonTitle("웹툰1").realUrl("url").toonAuthor("author")
            .toonContent("content").toonImg("img").toonPlatform("platform").build();
        webtoonRepository.save(webtoon);
        Review review = Review.builder().reviewContent("리뷰1").likeCount(10000).webtoon(webtoon)
            .user(user).build();
        reviewRepository.save(review);
        Review review2 = Review.builder().reviewContent("리뷰2").likeCount(10005).webtoon(webtoon)
            .user(user).build();
        reviewRepository.save(review2);
        Review review3 = Review.builder().reviewContent("리뷰3").likeCount(10003).webtoon(webtoon)
            .user(user).build();
        reviewRepository.save(review3);

        //when
        List<ReviewResponseDto> bestReviews = reviewRepository
            .getBestOrNewReview(ReviewStatus.BEST);

//        for (ReviewResponseDto bestReview : bestReviews) {
//            System.out.println("bestReview.getReviewContent() = " + bestReview.getReviewContent());
//        }

//        List<ReviewResponseDto> newReviews = reviewRepository
//            .getBestOrNewReview(ReviewStatus.NEW);
//
//        for (ReviewResponseDto newReview : newReviews) {
//            System.out.println("bestReview.getReviewContent() = " + newReview.getReviewContent());
//            System.out.println("bestReview.getReviewContent() = " + newReview.getCreateDate());
//        }

        //then
        //베스트순
        Assertions.assertThat(bestReviews.get(0).getReviewContent()).isEqualTo("리뷰2");
        Assertions.assertThat(bestReviews.get(1).getReviewContent()).isEqualTo("리뷰3");
        Assertions.assertThat(bestReviews.get(2).getReviewContent()).isEqualTo("리뷰1");

//        //최신순
//        Assertions.assertThat(newReviews.get(0).getReviewContent()).isEqualTo("리뷰3");
//        Assertions.assertThat(newReviews.get(1).getReviewContent()).isEqualTo("리뷰2");
//        Assertions.assertThat(newReviews.get(2).getReviewContent()).isEqualTo("리뷰1");
    }

    @Test
    void getNewReviewWithPageable() {
    }

    @Test
    void getBestReviewWithPageable() {
    }

    @Test
    void findMyReviews() {
    }

    @Test
    void findMyReviewsAndGenre() {
    }
}