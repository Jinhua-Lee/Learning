/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/runtime/PcRegisterTest.java
 * LastModified:    2020/10/17 下午4:09
 */

package com.jvm.runtime;

/**
 * PC程序计数器测试
 * @author Jinhua
 * @date 2020/10/17 16:09
 */
public class PcRegisterTest {

    public static void main(String[] args) {
        int i = 10;
        int j = 20;
        int k = i + j;

        String s = "abc";
        System.out.println("i = " + i);
        System.out.println("k = " + k);
    }
}
