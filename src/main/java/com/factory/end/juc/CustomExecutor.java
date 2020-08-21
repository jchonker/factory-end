package com.factory.end.juc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author jchonker
 * @Date 2020/8/19 16:48
 * @Version 1.0
 * 自定义线程池
 */
public class CustomExecutor {
    public static void main(String[] args) {
        //导入com.google.common.util.concurrent包
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        int size = 4;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);

        threadPoolExecutor.submit(new MyThread1());
        threadPoolExecutor.submit(new MyThread2());
    }
}
