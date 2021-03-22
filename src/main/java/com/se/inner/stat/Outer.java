package com.se.inner.stat;

/**
 * 静态内部类模拟
 *
 * @author Jinhua
 * @date 2021/2/18 22:07
 */
public class Outer {

    int a = 10;
    static int b = 5;

    public Outer() {
    }

    /**
     * 静态内部类不依赖于外部类
     */
    static class Inner {

        public Inner() {
            // 因为不依赖于外部类，所以外部类的加载与否，不能引用外部的静态，会报错
//            System.out.println(a);
            System.out.println(b);
        }
    }
}
