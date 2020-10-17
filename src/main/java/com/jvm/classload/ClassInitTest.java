/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/classload/ClassInitTest.java
 * LastModified:    2020/9/30 下午10:44
 */

package com.jvm.classload;

/**
 * @author Jinhua
 * @date 2020/9/30 21:59
 */
public class ClassInitTest {

    /**
     * Linking：Prepare阶段 num = 1
     * Initial：num = 1 -> num = 2
     */
    private static int num = 1;

    static {
        num = 2;
        number = 20;
        System.out.println("num = " + num);
        // 报错，非法前向引用
//        System.out.println("number = " + number);
    }

    /**
     * Linking：Prepare阶段 number = 0;
     * Initial: number = 20 -> number = 10(源文件中的顺序)
     */
    private static int number = 10;

    public static void main(String[] args) {
        // num == 10
        System.out.println("num = " + num);

        // number == 20
        System.out.println("number = " + number);
    }
}
