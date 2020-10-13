package com.factory.end.util.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author jchonker
 * @Date 2020/8/17 17:28
 * @Version 1.0
 */
//@Component
public class DistributedRedisLock {
    /**
     * 从配置类中获取redisson对象
     */
    private Redisson redisson;
    private final String LOCK_TITLE = "redisLock_";

    public DistributedRedisLock() {
        RedissonManager redissonManager = new RedissonManager();

        System.out.println("DistributedRedisLock初始化···");
        redisson = redissonManager.getRedisson();
    }

    /**
     * 加锁
     * @param lockName
     * @return
     */
    public boolean acquire(String lockName){
        //声明key对象
        String key = LOCK_TITLE + lockName;
        //获取锁对象
        RLock mylock = redisson.getLock(key);
        System.out.println("mylock:"+mylock);
        //加锁，并且设置锁过期时间，防止死锁的产生
        mylock.lock(2, TimeUnit.MINUTES);
        System.err.println("======lock======"+Thread.currentThread().getName());
        //加锁成功
        return  true;
    }

    /**
     * 锁的释放
     * @param lockName
     */
    public void release(String lockName){
        //必须是和加锁时的同一个key
        String key = LOCK_TITLE + lockName;
        //获取所对象
        RLock mylock = redisson.getLock(key);
        //释放锁（解锁）
        mylock.unlock();
        System.err.println("======unlock======"+Thread.currentThread().getName());

    }

}
