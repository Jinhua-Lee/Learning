package com.se.inner.anonymous;

/**
 * 匿名内部类模拟
 * <p>
 * 方法是【抽象类或接口】的虚方法，给出临时实现来创建对象
 *
 * @author Jinhua
 */
public class Outer {

    /**
     * 返回一个 Inner 对象
     *
     * @return Inner 对象
     */
    public static Inner method() {
        return () -> System.out.println("Hello");
    }

    public static void main(String[] args) {
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