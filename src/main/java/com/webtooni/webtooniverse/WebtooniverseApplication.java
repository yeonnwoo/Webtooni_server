package com.webtooni.webtooniverse;

import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebtooniverseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebtooniverseApplication.class, args);





    }

}
