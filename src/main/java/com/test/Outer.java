package com.test;

/**
 * 外部方法
 *
 * @author Jinhua
 */
public class Outer {

    /**
     * 返回一个 Inner 对象
     * @return Inner 对象
     */
    public static Inner method() {
        return () -> System.out.println("Hello");
    }

    public static void main(String[] args) {
        String s = "Hello";
        Outer.method().show();
    }
}

/**
 * 内部接口
 */
interface Inner {

    /**
     * 展示方法
     */
    void show();
}