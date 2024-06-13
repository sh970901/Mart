package com.hun.market.core.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.redis.cache")
@Configuration
public class RedisProperties {

    private String host;
    private int port;

}
