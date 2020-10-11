/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/ParentAndChildInitTest.java
 * LastModified:    2020/9/30 下午11:04
 */

package com.jvm;

/**
 * 父类和子类加载的<clinit>方法前后关系
 * @author Jinhua
 * @date 2020/9/30 23:04
 */
public class ParentAndChildInitTest {

    /**
     * 父类
     */
    static class Parent {
        public static int a = 1;

        static {
            a = 2;
        }
    }

    /**
     * 子类
     */
    static class Child extends Parent {
        public static int b = a;
    }

    public static void main(String[] args) {
        // 先加载Parent类，再加载Child类，结果为2
        System.out.println(Child.b);
    }

}
