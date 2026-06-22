package com.example.journal_app.serviceTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RedisTest {

    @Autowired
    // private StringRedisTemplate srt;
    private RedisTemplate<String, String> redisTemplate;

    @Disabled
    @Test
    void redisTest() {
        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        vo.set("name", "xeon");
        log.info("Name is : " + vo.get("name"));
        log.info("Message is : " + vo.get("message"));
    }

}
