package com.factory.end.test.uuid;

import java.util.UUID;

/**
 * @Author jchonker
 * @Date 2020/8/31 16:11
 * @Version 1.0
 */
public class UUIDTest {
    public static void main(String[] args) throws InterruptedException {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        //20200831000001

        for(int i=0;i < 10;i++){
            Thread.sleep(1);
            long timeMillis = System.currentTimeMillis();
            System.out.println("F"+timeMillis);
        }
    }
}
