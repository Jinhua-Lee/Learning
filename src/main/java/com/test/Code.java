package com.test;

/**
 * 不同代码块加载顺序 Demo
 *
 * @author Jinhua
 */
public class Code {
    static {
        System.out.println("静态代码块");
    }

    public Code() {
        System.out.println("构造方法");
    }

    {
        System.out.println("构造代码块");
    }

    public void test() {
        {
            System.out.println("局部代码块");
        }
    }

    public static void main(String[] args) {
        new Code().test();
    }
}
