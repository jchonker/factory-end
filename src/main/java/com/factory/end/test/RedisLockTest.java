package com.factory.end.test;

import com.factory.end.javabean.RedisBean;
import com.factory.end.util.redis.DistributedRedisLock;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author jchonker
 * @Date 2020/8/18 0:08
 * @Version 1.0
 * 测试redis分布式锁
 */
public class RedisLockTest {
    Logger logger = LoggerFactory.getLogger(RedisLockTest.class);

    public static void main(String[] args) throws InterruptedException {

    }

    @Test
    public void test() throws InterruptedException {
        DistributedRedisLock distributedRedisLock = new DistributedRedisLock();
        System.out.println(distributedRedisLock);
        logger.info("定义锁名称");
        String key = "test";
        //加锁
        logger.info("加锁");
        distributedRedisLock.acquire(key);
        //执行具体业务逻辑
        logger.info("执行具体业务逻辑");
        for(int i = 0;i < 10;i++){
            logger.info("输出:"+i);
            Thread.sleep(5000);
        }
        //释放锁
        logger.info("释放锁");
        distributedRedisLock.release(key);
    }

    @Autowired
    public RedisBean redisBean;

    @Test
    public void test2(){
    }

    @Autowired
    private Environment env;

    @Test
    public void test3(){
        System.out.println(env);
    }
}
