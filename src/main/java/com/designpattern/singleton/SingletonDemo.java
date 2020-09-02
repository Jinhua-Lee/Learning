/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/singleton/SingletonDemo.java
 * LastModified:    2020/8/23 下午12:20
 */

package com.designpattern.singleton;

import lombok.SneakyThrows;

/**
 * 单例模式的多线程环境演示类
 * @author Jinhua
 * @date 2020/8/23 12:20
 */
public class SingletonDemo implements Runnable{

    /**
     * 测试饿汉模式
     */
    public void testHungry() {
        System.out.println(SingletonHungry.getInstance().hashCode());
    }

    /**
     * 测试懒汉模式（线程不安全）
     * @throws InterruptedException
     */
    public void testSingletonLazy() throws InterruptedException {
        System.out.println(SingletonLazy.getInstance().hashCode());
    }

    /**
     * 测试懒汉模式（线程安全）
     * @throws InterruptedException
     */
    public void testSingletonLazyThread() throws InterruptedException {
        System.out.println(SingletonLazyThread.getInstance().hashCode());
    }

    /**
     * 测试静态内部类实现单例模式
     */
    public void testSingletonStaticInnerClass() {
        System.out.println(SingletonStaticInnerClass.getInstance().hashCode());
    }

    public void testSingleton() {
        SingletonDemo r1 = new  SingletonDemo();
        SingletonDemo r2 = new  SingletonDemo();
        SingletonDemo r3 = new  SingletonDemo();
        Thread t1 = new Thread(r1, "线程1");
        Thread t2 = new Thread(r2, "线程2");
        Thread t3 = new Thread(r3, "线程3");
        t1.start();
        t2.start();
        t3.start();
    }

    @SneakyThrows
    @Override
    public void run() {
        testSingletonLazy();
    }

    public static void main(String[] args) {
        new SingletonDemo().testSingleton();
    }
}
