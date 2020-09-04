package com.factory.end.util.timer;

/**
 * @Author jchonker
 * @Date 2020/9/3 17:17
 * @Version 1.0
 */
public class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("开始执行任务");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束执行任务");
    }
}
