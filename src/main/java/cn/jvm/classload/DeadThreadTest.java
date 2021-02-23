/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/classload/DeadThreadTest.java
 * LastModified:    2020/10/12 下午10:46
 */

package cn.jvm.classload;

/**
 * 一个线程对某个类进行部分加载，另一个线程是否可以进入(单例模式参考)
 * @author Jinhua
 * @date 2020/10/12 22:20
 */
public class DeadThreadTest {

    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " 开始！！！");
            new DeadThread();
            System.out.println(Thread.currentThread().getName() + " 结束！！！");
        };

        Thread t1 = new Thread(r, "线程1");
        Thread t2 = new Thread(r, "线程2");

        /*
         * 只有一个线程会执行加载static块的过程。
         */
        t1.start();
        t2.start();
    }
}

class DeadThread {

    static {
        if (true) {
            System.out.println(Thread.currentThread().getName() + "初始化当前类...");
            while (true) {

            }
        }
    }
}