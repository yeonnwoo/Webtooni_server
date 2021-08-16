package com.webtooni.webtooniverse;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableCaching
@RequiredArgsConstructor
public class WebtooniverseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebtooniverseApplication.class, args);
    }
}
