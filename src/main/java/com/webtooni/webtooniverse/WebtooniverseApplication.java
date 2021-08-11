package com.webtooni.webtooniverse;

import com.webtooni.webtooniverse.domain.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableJpaAuditing
@SpringBootApplication
@EnableCaching
@RequiredArgsConstructor
public class WebtooniverseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebtooniverseApplication.class, args);
    }
}
