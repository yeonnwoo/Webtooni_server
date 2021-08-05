package com.webtooni.webtooniverse.domain.review.service;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewBestResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewMainResponseDto;
import com.webtooni.webtooniverse.domain.review.dto.response.ReviewNewResponseDto;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewService reviewService;

    @BeforeEach
    void getTestData(){
        Review review1 = new Review("테스트내용1", 1.5f, 1);
        Review review2 = new Review("테스트내용2", 2.0f, 2);
        Review review3 = new Review("테스트내용3", 2.5f, 3);
        Review review4 = new Review("테스트내용4", 3.0f, 4);
        Review review5 = new Review("테스트내용5", 3.5f, 5);
        Review review6 = new Review("테스트내용6", 4.0f, 6);
        Review review7 = new Review("테스트내용7", 4.5f, 7);
        Review review8 = new Review("테스트내용8", 5.0f, 8);
        reviewRepository.save(review1);
        reviewRepository.save(review2);
        reviewRepository.save(review3);
        reviewRepository.save(review4);
        reviewRepository.save(review5);
        reviewRepository.save(review6);
        reviewRepository.save(review7);
        reviewRepository.save(review8);
    }

    @DisplayName("리뷰 베스트/최신순 조회")
    @Test
    public void test(){
        //given

        //when
        List<ReviewBestResponseDto> reviewBestResponseDto = reviewService.getMainReview().getBestReview();
        List<ReviewNewResponseDto> reviewNewResponseDto = reviewService.getMainReview().getNewReview();
        ReviewMainResponseDto reviewMainResponseDto = reviewService.getMainReview();
        //then
        for (ReviewBestResponseDto responseDto : reviewBestResponseDto){
            System.out.println("responseDto.getUserPointNumber()= " + responseDto.getUserPointNumber());
        }
        for (ReviewNewResponseDto responseDto1 : reviewNewResponseDto){
            System.out.println("responseDto1.getCreateDate()= " + responseDto1.getCreateDate());
        }
        ReviewMainResponseDto responseDto2 = new ReviewMainResponseDto(reviewBestResponseDto, reviewNewResponseDto);
            System.out.println("responseDto2.getBestReview()= " + responseDto2.getBestReview() +
                    "responseDto2.getNewReview" + responseDto2.getNewReview());



    }
}