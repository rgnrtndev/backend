package com.rcc.dev.backend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@ComponentScan(basePackages = {"com.rcc.dev.backend.*"})
@EnableJpaRepositories(basePackages = "com.rcc.dev.backend.repository")
@EntityScan(basePackages = "com.rcc.dev.backend.model")
public class InitConfig {

//    @Order(1)
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        var requestFactory = new SimpleClientHttpRequestFactory();
//        requestFactory.setOutputStreaming(false);
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(requestFactory);
        var restTemplate = restTemplateBuilder
                .setReadTimeout(Duration.ofSeconds(500))
                .setConnectTimeout(Duration.ofSeconds(500))
                .build();
        restTemplate.setRequestFactory(factory);
//        restTemplate.setErrorHandler(new CommonResponseErrorHandler<>(ErrorDetail.class));
        return restTemplate;
    }

    @Order(2)
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
