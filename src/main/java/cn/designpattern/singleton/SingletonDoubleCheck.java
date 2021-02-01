/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/singleton/SingletonDoubleCheck.java
 * LastModified:    2020/8/22 下午10:39
 */

package cn.designpattern.singleton;

/**
 * 双重检查的单例模式
 *
 * @author Jinhua
 * @date 2020/8/22 22:39
 */
public class SingletonDoubleCheck implements MySingleton {

    private static volatile SingletonDoubleCheck instance = null;

    private SingletonDoubleCheck() {

    }

    public static SingletonDoubleCheck getInstance() {
        // 避免多次进入同步判断，影响性能
        if (instance == null) {
            synchronized (SingletonDoubleCheck.class) {
                // 同步块中的判断，保证唯一
                if (instance == null) {
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        return instance;
    }

    @Override
    public void doSomething() {
        System.out.println(this + "\t双重检查单例模式");
    }
}

