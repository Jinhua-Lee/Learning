/*
 * Copyright (c)    2020/8/15 下午10:24.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/test/HasStatic.java
 * LastModified:    2020/8/15 下午10:24
 */

package com.test;

/**
 * 对象引用所属类的静态变量测试
 *
 * @author Jinhua
 * @date 2020/8/15 22:24
 */
public class HasStatic {

    private static int x = 100;

    public static void main(String args[]) {
        HasStatic hs1 = new HasStatic();
        hs1.x++;
        HasStatic hs2 = new HasStatic();
        hs2.x++;
        hs1 = new HasStatic();
        hs1.x++;
        HasStatic.x--;
        System.out.println("x=" + x);
    }
}
