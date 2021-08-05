package com.webtooni.webtooniverse.domain.webtoon.service;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WebtoonServiceTest {

    @Autowired
    WebtoonRepository webtoonRepository;

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
    }

    @Test
    void 이번달_종합_웹툰_순위(){
        //given
    }
}