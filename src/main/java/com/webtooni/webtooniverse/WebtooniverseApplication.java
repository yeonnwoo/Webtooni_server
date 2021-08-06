package com.webtooni.webtooniverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WebtooniverseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebtooniverseApplication.class, args);
    }

}
