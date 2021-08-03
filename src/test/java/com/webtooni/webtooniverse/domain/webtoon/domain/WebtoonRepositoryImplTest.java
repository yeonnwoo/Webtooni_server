package com.webtooni.webtooniverse.domain.webtoon.domain;

import com.webtooni.webtooniverse.domain.join.*;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class WebtoonRepositoryImplTest {


    @Autowired
    WebtoonRepository webtoonRepository;
    @Autowired
    UserGenreRepository userGenreRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    WebtoonGenreRepository webtoonGenreRepository;

    @BeforeEach
    void getDummyData(){
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        Webtoon webtoon1 = new Webtoon("웹툰1", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 4.0f, 0, false, 4);
        Webtoon webtoon2 = new Webtoon("웹툰2", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 4.0f, 0, false, 4);
        Webtoon webtoon3 = new Webtoon("웹툰3", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 4.0f, 0, false, 4);
        Webtoon webtoon4 = new Webtoon("웹툰4", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 3.0f, 0, true, 4);
        Webtoon webtoon5 = new Webtoon("웹툰5", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 4.0f, 0, true, 4);
        Webtoon webtoon6 = new Webtoon("웹툰6", "작가1", "내용1", "이미지1", "월", "url", "15", "네이버", 5.0f, 0, true, 4);
        webtoonRepository.save(webtoon1);
        webtoonRepository.save(webtoon2);
        webtoonRepository.save(webtoon3);
        webtoonRepository.save(webtoon4);
        webtoonRepository.save(webtoon5);
        webtoonRepository.save(webtoon6);
        Review review1 = new Review(user1,5.0f,webtoon1);
        Review review2 = new Review(user2,5.0f,webtoon2);
        Review review3 = new Review(user2,5.0f,webtoon3);
        Review review4 = new Review(user3,5.0f,webtoon1);
        Review review5 = new Review(user3,5.0f,webtoon5);
        reviewRepository.save(review1);
        reviewRepository.save(review2);
        reviewRepository.save(review3);
        reviewRepository.save(review4);
        reviewRepository.save(review5);
        Genre genre1 = new Genre("코믹");
        Genre genre2 = new Genre("멜로");
        Genre genre3 = new Genre("액션");
        genreRepository.save(genre1);
        genreRepository.save(genre2);
        genreRepository.save(genre3);
        UserGenre userGenre1 = new UserGenre(user1, genre1);
        UserGenre userGenre2 = new UserGenre(user2, genre2);
        UserGenre userGenre3 = new UserGenre(user2, genre3);
        userGenreRepository.save(userGenre1);
        userGenreRepository.save(userGenre2);
        userGenreRepository.save(userGenre3);
        WebtoonGenre webtoonGenre1 = new WebtoonGenre(genre1, webtoon1);
        WebtoonGenre webtoonGenre2 = new WebtoonGenre(genre2, webtoon2);
        WebtoonGenre webtoonGenre3 = new WebtoonGenre(genre3, webtoon3);
        WebtoonGenre webtoonGenre4 = new WebtoonGenre(genre1, webtoon4);
        WebtoonGenre webtoonGenre5 = new WebtoonGenre(genre2, webtoon5);
        WebtoonGenre webtoonGenre6 = new WebtoonGenre(genre3, webtoon6);
        webtoonGenreRepository.save(webtoonGenre1);
        webtoonGenreRepository.save(webtoonGenre2);
        webtoonGenreRepository.save(webtoonGenre3);
        webtoonGenreRepository.save(webtoonGenre4);
        webtoonGenreRepository.save(webtoonGenre5);
        webtoonGenreRepository.save(webtoonGenre6);
    }

}