package com.factory.end.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @Author jchonker
 * @Date 2020/10/9 10:46
 * @Version 1.0
 * 创建一个配置类，这里注意要使用HttpComponentsClientHttpRequestFactory作为RestTemplate构造方法的入参，
 * 这样才能支持gzip压缩的response，否则得到的就是乱码
 */
@Configuration
public class WeatherConfig {
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
