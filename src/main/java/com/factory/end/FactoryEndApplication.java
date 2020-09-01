package com.factory.end;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@SpringBootApplication
@EnableScheduling
@EnableCaching
//@MapperScan(basePackages = "com.factory.end.mapper")
//@EnableJpaRepositories("com.factory.end.mapper")
//@Component
public class FactoryEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(FactoryEndApplication.class, args);
    }


}
