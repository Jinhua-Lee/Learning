/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/runtime/stack/StringBuilderTest.java
 * LastModified:    2020/10/28 下午10:14
 */

package cn.jvm.runtime.stack;

/**
 * 面试题：方法中定义的局部变量是否线程安全？
 *      局部变量：
 *          1. 基本类型；
 *          2. 引用类型
 *      线程安全：
 *          1. 只有一个线程操作此数据，则必定是线程安全的。
 *          2. 若有多线程并发操作此数据，则此数据是共享数据，不考虑同步机制，则会存在线程安全问题。
 *      测试 StringBuilder, 线程不安全，用本身不安全的类来说事
 * @author Jinhua
 * @date 2020/10/28 22:14
 */
public class StringBuilderTest {

    /**
     * s1的声明方式是线程安全的，在方法内部定义
     */
    public static void method1() {
        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i < Byte.MAX_VALUE; i++) {
            s1.append("a");
        }
    }

    /**
     * s2线程不安全，它是从外部传入的，可能其他地方也有对该变量的操作。
     * @param s2 外部入参 StringBuilder，变量所属类没有线程安全机制
     */
    public static void method2(StringBuilder s2) {
        for (int i = 0; i < Byte.MAX_VALUE; i++) {
            synchronized (s2) {
                s2.append("a");
                System.out.print("a");
            }
        }
    }

    /**
     * 作为返回值，和s2同理，返回出去的值可能被多个地方并发操作
     * @return 返回 StringBuilder
     */
    public static StringBuilder method3() {
        StringBuilder s3 = new StringBuilder();
        for (int i = 0; i < Byte.MAX_VALUE; i++) {
            s3.append("a");
        }
        return s3;
    }

    /**
     * s3 在内部消亡了。返回的是一个新的对象，并不是上s3本身
     * @return 返回String 类型，
     */
    public static String method4() {
        StringBuilder s3 = new StringBuilder();
        for (int i = 0; i < Byte.MAX_VALUE; i++) {
            s3.append("a");
        }
        return s3.toString();
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        Thread t1 = new Thread(() -> {
            method2(sb);
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < Byte.MAX_VALUE; i++) {
                synchronized (sb) {
                    sb.append("b");
                    System.out.print("b");
                }
            }
        });

        t1.start();
        t2.start();
    }

}
