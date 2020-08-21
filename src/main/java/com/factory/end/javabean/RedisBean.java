package com.factory.end.javabean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Author jchonker
 * @Date 2020/8/18 13:48
 * @Version 1.0
 */
@Component
//@ConfigurationProperties(prefix = "redis")
@Data
public class RedisBean{
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private String port;
    @Value("${redis.password}")
    private String password;

}
