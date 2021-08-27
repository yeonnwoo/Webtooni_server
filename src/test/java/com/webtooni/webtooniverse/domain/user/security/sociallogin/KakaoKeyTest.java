package com.webtooni.webtooniverse.domain.user.security.sociallogin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class KakaoKeyTest {
    @Value("${kakao.clientId}")
    private String kakaoClientID;

    @Test
    public void contextLoads() throws Exception{
        log.info("kakaoClientId : {}", kakaoClientID);
    }

}