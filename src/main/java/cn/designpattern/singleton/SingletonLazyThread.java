/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/singleton/SingletonLazyThread.java
 * LastModified:    2020/8/22 下午10:52
 */

package cn.designpattern.singleton;

/**
 * 懒汉模式单例（线程安全）
 *
 * @author Jinhua
 * @date 2020/8/22 22:52
 */
public class SingletonLazyThread implements MySingleton {

    private static SingletonLazyThread instance;

    private SingletonLazyThread() {

    }

    /**
     * 测试多线程环境下线程安全的懒汉实现单例
     *
     * @return 单例对象
     * @throws InterruptedException 中断异常
     */
    public static synchronized SingletonLazyThread getInstance() throws InterruptedException {
        if (instance == null) {
            // 睡眠用于模拟线程的问题
            Thread.sleep(5000);
            instance = new SingletonLazyThread();
        }
        return instance;
    }

    @Override
    public void doSomething() {
        System.out.println(this + "\t线程安全的懒汉单例模式.");
    }
}
