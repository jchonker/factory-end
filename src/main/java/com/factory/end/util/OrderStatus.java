package com.factory.end.util;

/**
 * @Author jchonker
 * @Date 2020/9/2 17:43
 * @Version 1.0
 * 订单状态
 */
public class OrderStatus {
    public static final Integer UNCONFIRMED = 1;

    public static final Integer CONFIRMED = 2;

    public static final Integer  FALLBACK = 3;

    public static final Integer SCHEDULING = 4;

    public static final Integer WAITPRODUCE = 5;

    public static final Integer PRODUCING = 6;

    public static final Integer  PRODUCECOMPLETE = 7;
}
