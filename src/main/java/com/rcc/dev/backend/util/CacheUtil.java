package com.rcc.dev.backend.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheUtil {
    private final RedisTemplate<String, Object> redisTemplate;

    public void putCache(String cacheName,  Object value) {
        redisTemplate.opsForValue().set(cacheName , value);
    }

    public Object getCache(String cacheName) {
        return redisTemplate.opsForValue().get(cacheName);
    }
}
