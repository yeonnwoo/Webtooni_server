package com.webtooni.webtooniverse.domain.webtoon.service;

import com.webtooni.webtooniverse.domain.Genre.domain.Genre;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    /**
     * 웹툰 상세 페이지 - 웹툰 기본정보, 리뷰 List
     * @param - 해당 웹툰 id
     * @return - WebtoonDetailDto
     */
    public WebtoonDetailDto getDetailAndReviewList(Long id)
    {
        //해당 웹툰 정보 찾기
        Webtoon webtoon = webtoonRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id는 존재하지 않습니다.")
        );

        //해당 웹툰의 장르 찾기
        List<Genre> WebToonGenre = webtoonRepository.findWebToonGenre(webtoon);

        //해당 웹툰의 리뷰 찾기
        List<Review> reviewList = webtoonRepository.findReviewByWebToonId(id);

        return new WebtoonDetailDto(webtoon,WebToonGenre,reviewList);

    }

}
