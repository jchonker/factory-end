package com.factory.end.util.redis;

import com.factory.end.javabean.RedisBean;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author jchonker
 * @Date 2020/8/17 17:25
 * @Version 1.0
 */
//@Component
public class RedissonManager {
    private final Logger logger = LoggerFactory.getLogger(RedissonManager.class);

    @Autowired
    private Environment environment;

    /**
     * 注意：要导入org.redisson.config包下的类
     */
    private Config config = new Config();
    /**
     * 声明redisso对象
     */
    private Redisson redisson = null;

    /**
     * redis的ip地址
     */
    private String ip;

    /**
     * redis的port
     */
    private String port;

    /**
     * redis的密码
     */
    private String password;

    @Resource
    private RedisBean redisBean;

    /**
     * 构造方法
     */
    public RedissonManager() {
        ip = "106.53.220.55";
        port = "6379";
        password = "123abc456";

        //logger.info(ip+":"+port+":"+password);
        config.useSingleServer().setAddress("redis://"+ip+":"+port).setPassword(password);
        //设置超时时间
        config.setLockWatchdogTimeout(5000);
        /**
         * 得到redisson对象
         */
        redisson = (Redisson) Redisson.create(config);
    }

    /**
     * 获取redisson对象的方法
     * @return
     */
    public Redisson getRedisson(){
        return redisson;
    }

}
