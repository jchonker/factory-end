package com.factory.end.test;

import com.factory.end.util.http.HttpStatusEnum;

/**
 * @Author jchonker
 * @Date 2020/8/27 21:54
 * @Version 1.0
 */
public class EnumTest {
    public static void main(String[] args) {
        System.out.println(HttpStatusEnum.OK.code() + " " + HttpStatusEnum.OK.reasonPhraseCN() + " " + HttpStatusEnum.OK.reasonPhraseUS());
    }
}
