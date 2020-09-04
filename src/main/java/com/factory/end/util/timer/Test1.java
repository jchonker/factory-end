package com.factory.end.util.timer;

/**
 * @Author jchonker
 * @Date 2020/9/3 18:04
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        TimerTest timerTest = new TimerTest();
        timerTest.start();
    }

    TimerTest timerTest = new TimerTest();

    @org.junit.Test
    public void start(){
        timerTest.start();
    }

    @org.junit.Test
    public void stop(){
        timerTest.stop();
    }
}
