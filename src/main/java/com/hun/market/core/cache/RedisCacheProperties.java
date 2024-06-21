package com.hun.market.core.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.redis.cache")
@Configuration
public class RedisCacheProperties extends RedisProperties {
}
