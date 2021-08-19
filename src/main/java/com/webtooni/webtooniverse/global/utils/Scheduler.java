package com.webtooni.webtooniverse.global.utils;

import com.webtooni.webtooniverse.domain.webtoon.dto.response.BestReviewerWebtoonResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class Scheduler {

    private final RedisTemplate redisTemplate;
    private final WebtoonService webtoonService;

//    @Scheduled(cron = "1 * * * * *")
//    public void redisConnectionTest() {
//        BestReviewerWebtoonResponseDto bestReviewerWebtoonResponseDto = webtoonService
//            .getBestReviewerWebtoon();
//        System.out.println("저장 완료");
//
//        final ValueOperations<String, BestReviewerWebtoonResponseDto> valueOperation = redisTemplate.opsForValue();
//        redisTemplate.delete("bestReviewerWebtoon");
//
//        BestReviewerWebtoonResponseDto bestReviewerWebtoons = valueOperation.get("bestReviewerWebtoon");
//        System.out.println("bestReviewerWebtoons = " + bestReviewerWebtoons);
//
//        valueOperation.set("bestReviewerWebtoon", bestReviewerWebtoonResponseDto);
//
//        BestReviewerWebtoonResponseDto bestReviewerWebtoon = valueOperation.get("bestReviewerWebtoon");
//        System.out.println("bestReviewerWebtoon = " + bestReviewerWebtoon);
//
//    }
}

