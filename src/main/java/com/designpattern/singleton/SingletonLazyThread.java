/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/singleton/SingletonLazyThread.java
 * LastModified:    2020/8/22 下午10:52
 */

package com.designpattern.singleton;

/**
 * 懒汉模式单例（线程安全）
 * @author Jinhua
 * @date 2020/8/22 22:52
 */
public class SingletonLazyThread {

    private static SingletonLazyThread instance;

    private SingletonLazyThread(){

    }

    /**
     * 测试多线程环境下线程安全的懒汉实现单例
     * @return 单例对象
     * @throws InterruptedException
     */
    public static synchronized SingletonLazyThread getInstance() throws InterruptedException {
        if (instance == null) {
            Thread.sleep(5000);
            instance = new SingletonLazyThread();
        }
        return instance;
    }
}
