package com.factory.end.juc;

import lombok.SneakyThrows;

/**
 * @Author jchonker
 * @Date 2020/8/19 16:58
 * @Version 1.0
 */
public class MyThread1 implements Runnable {
    @SneakyThrows
    @Override
    public void run() {
        System.out.println("线程1开始运行");
        for(int i = 0; i < 10;i++){
            System.out.println("线程1-----"+i);
            Thread.sleep(500);
        }
        System.out.println("线程1结束运行");
    }
}
