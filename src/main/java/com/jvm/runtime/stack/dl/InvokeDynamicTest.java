/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/runtime/stack/dl/InvokeDynamicTest.java
 * LastModified:    2020/10/25 下午11:39
 */

package com.jvm.runtime.stack.dl;

/**
 * 动态方法调用举例
 * @author Jinhua
 * @date 2020/10/25 23:13
 */
public class InvokeDynamicTest {

    private String str;

    public InvokeDynamicTest(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    /**
     * 函数式接口动态方法调用
     * @param testFunction 动态方法
     */
    public void lambda(TestFunction testFunction) {
        testFunction.func(str);
    }

    public static void main(String[] args) {

        InvokeDynamicTest idt = new InvokeDynamicTest("Hello");

        /*
         * 方式1：先实现方式，再传入方法
         */
        // tf的接口入参执行println方法
        TestFunction tf = System.out::println;
        // 传入方法
        idt.lambda(tf);

        /*
         * 方式2：匿名方式传入
         */
        idt.lambda(System.out::println);
    }
}

/**
 * 函数式接口：有且仅有一个抽象方法（）
 */
interface TestFunction {

    /**
     * 接口方法
     * @param str 字符串入参
     */
    void func(String str);
}

