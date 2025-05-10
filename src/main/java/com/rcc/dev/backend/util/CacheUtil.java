package com.rcc.dev.backend.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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

    public Object getCache(String cacheName, Object key) {
        return this.redisTemplate.opsForValue().get(cacheName + "|" + String.valueOf(key));
    }
    public Boolean deleteCache(String cacheName){
        return redisTemplate.delete(cacheName);
    }

    public String getCacheString(String cacheName, Object key) {
        Object cache = this.redisTemplate.opsForValue().get(cacheName + "|" + String.valueOf(key));
        String result;
        if (Objects.isNull(cache)) {
            result = "";
        } else {
            result = cache.toString();
        }

        return result;
    }

    public void putCacheWithTTL(String cacheName, Object key, Object value, long timeToLive) {
        this.redisTemplate.opsForValue().set(cacheName + "|" + String.valueOf(key), value, timeToLive, TimeUnit.SECONDS);
    }

    public void removeCache(String cacheName, Object key) {
        this.redisTemplate.opsForValue().getOperations().delete(cacheName + "|" + String.valueOf(key));
    }
}
