/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/runtime/stack/LocalVariablesTest.java
 * LastModified:    2020/10/19 下午10:17
 */

package com.jvm.runtime.stack;

import java.util.Date;

/**
 * @author Jinhua
 * @date 2020/10/19 22:17
 */
public class LocalVariablesTest {
    private int count = 0;

    public static void main(String[] args) {
        LocalVariablesTest lvt = new LocalVariablesTest();
        int num = 10;
        lvt.test1();
    }

    private void test1() {
        Date date = new Date();
        String str = "str";
        System.out.println(date + str);
    }

    private String test2(Date dateP, String nameP) {

        dateP = null;
        nameP = "ljh";
        double weight = 130.5;
        char gender = '男';
        return dateP + nameP;
    }

    private void test3() {
        count++;
    }

    private void test4() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        int c = a + 1;
    }
}
