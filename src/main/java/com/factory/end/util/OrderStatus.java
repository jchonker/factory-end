package com.factory.end.util;

/**
 * @Author jchonker
 * @Date 2020/9/2 17:43
 * @Version 1.0
 * 订单状态
 */
public class OrderStatus {
    public static final String UNCONFIRMED = "1";

    public static final String CONFIRMED = "2";

    public static final String  FALLBACK = "3";

    public static final String SCHEDULING = "4";

    public static final String WAITPRODUCE = "5";

    public static final String PRODUCING = "6";

    public static final String  PRODUCECOMPLETE = "7";
}
