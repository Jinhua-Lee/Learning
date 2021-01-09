/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/runtime/stack/lv/LocalVariablesTest.java
 * LastModified:    2020/10/21 下午10:45
 */

package cn.jvm.runtime.stack.lv;

import java.util.Date;

/**
 * 局部变量表测试：
 *      通过jClassLib工具查看
 * @author Jinhua
 * @date 2020/10/19 22:17
 */
public class LocalVariablesTest {

    private int count = 0;

    public static void main(String[] args) {
        LocalVariablesTest lvt = new LocalVariablesTest();
        int num = 10;
        lvt.test4();
    }

    private void test1() {
        Date date = new Date();
        String str = "str";
        System.out.println(date + str);
    }

    /**
     * 各种类型的数据所占slot
     * @param dateP date类型参数
     * @param nameP String类型参数
     * @return String返回值
     */
    private String test2(Date dateP, String nameP) {
        dateP = null;
        nameP = "ljh";
        double weight = 130.5;
        char gender = '男';
        return dateP + nameP;
    }

    /**
     * 类的实例方法，包含this局部变量
     */
    private void test3() {
        count++;
    }

    /**
     * 过期的局部变量可能复用之前的局部变量的Slot
     *      1. this
     *      2. a
     *      3. b -> 在局部代码块有效
     *      4. c
     */
    private void test4() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        // 变量c使用已经销毁的变量b的slot的位置
        int c = a + 1;
    }
}
