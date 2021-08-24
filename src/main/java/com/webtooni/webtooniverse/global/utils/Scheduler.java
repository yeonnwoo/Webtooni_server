package com.webtooni.webtooniverse.global.utils;

import com.webtooni.webtooniverse.domain.webtoon.dto.response.BestReviewerWebtoonResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.MonthRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankListResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.RankTotalResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreListResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import java.util.List;
import java.util.Set;
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

    @Scheduled(cron = "0 0 0 * * *")
    public void redisConnectionTest() {
        //redis cache 삭제
        redisTemplate.delete("bestReviewerWebtoon");
        redisTemplate.delete("monthTotalRank");
        redisTemplate.delete("monthNaverRank");
        redisTemplate.delete("monthKaKaoRank");

        //caching
        BestReviewerWebtoonResponseDto bestReviewerWebtoon = webtoonService
            .getBestReviewerWebtoon();
        Set<RankTotalResponseDto> monthTotalRank = webtoonService.getMonthTotalRank();
        List<PlatformRankResponseDto> monthNaverRank = webtoonService.getMonthNaverRank();
        List<PlatformRankResponseDto> monthKakaoRank = webtoonService.getMonthKakaoRank();
    }
}

