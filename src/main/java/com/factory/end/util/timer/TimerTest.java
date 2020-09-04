package com.factory.end.util.timer;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @Author jchonker
 * @Date 2020/9/3 17:03
 * @Version 1.0
 */
public class TimerTest {
    public static void main(String[] args) {
        //timer1();
        //timer2();
        //timer3();
        //timer4();

        //ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        TimerTest timerTest = new TimerTest();

        timerTest.start();


        //用scheduleAtFixedRate代替schedule

    }

    ScheduledExecutorService scheduledExecutorService;


    @Test
    public void start(){
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        MyTask myTask = new MyTask();
        long initialDelay=500;
        long period=5000;
        scheduledExecutorService.scheduleAtFixedRate(myTask,initialDelay,period, TimeUnit.MILLISECONDS);
    }

    @Test
    public  void stop(){
        scheduledExecutorService.shutdownNow();
    }

    /**
     * 第一种方法：设定指定任务task在指定时间time执行 schedule(TimerTask task, Date time)
     */
    public static void timer1() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, 2000);// 设定指定的时间time,此处为2000毫秒
    }

    /**
     * 第二种方法：设定指定任务task在指定延迟delay后进行固定延迟peroid的执行
     * schedule(TimerTask task, long delay, long period)
     */
    public static void timer2() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, 1000, 5000);
    }

    /**
     * // 第三种方法：设定指定任务task在指定延迟delay后进行固定频率peroid的执行。
     *     // scheduleAtFixedRate(TimerTask task, long delay, long period)
     */
    public static void timer3() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, 1000, 2000);
    }

    /**
     * // 第四种方法：安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行．
     *     // Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
     */
    public static void timer4() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12); // 控制时
        calendar.set(Calendar.MINUTE, 0);    // 控制分
        calendar.set(Calendar.SECOND, 0);    // 控制秒

        Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的12：00：00

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
    }
}
