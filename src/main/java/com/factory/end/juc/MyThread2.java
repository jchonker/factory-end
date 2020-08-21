package com.factory.end.juc;

import lombok.SneakyThrows;

/**
 * @Author jchonker
 * @Date 2020/8/19 16:59
 * @Version 1.0
 */
public class MyThread2 implements Runnable {
    @SneakyThrows
    @Override
    public void run() {
        System.out.println("线程2开始运行");
        for(int i = 0; i < 10;i++){
            System.out.println("线程2-----"+i);
            Thread.sleep(500);
        }
        System.out.println("线程2结束运行");
    }
}
