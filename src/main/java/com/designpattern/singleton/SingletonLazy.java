/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/singleton/SingletonLazy.java
 * LastModified:    2020/8/22 下午10:49
 */

package com.designpattern.singleton;

/**
 * 懒汉模式单例（线程不安全）
 * @author Jinhua
 * @date 2020/8/22 22:49
 */
public class SingletonLazy {

    private static SingletonLazy instance = null;

    private SingletonLazy(){

    }

    /**
     * 模拟多线程下不安全的问题
     * @return 返回单例对象
     * @throws InterruptedException
     */
    public static SingletonLazy getInstance() throws InterruptedException {
        if (instance == null) {
            Thread.sleep(5000);
            instance = new SingletonLazy();
        }
        return instance;
    }
}
