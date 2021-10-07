//package com.webtooni.webtooniverse.domain.redis;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//
//import javax.transaction.Transactional;
//import org.springframework.test.context.TestPropertySource;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Transactional
//public class RedisTest {
//
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    @Test
//    void redisConnectionTest() {
//        final String key = "a";
//        final String data = "1";
//
//        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set(key, data);
//
//        final String result = valueOperations.get(key);
//        assertThat(result).isEqualTo(data);
//
//        redisTemplate.delete("a");
//        final String deleteResult = valueOperations.get(key);
//        assertThat(deleteResult).isNull();
//
//    }
//}
