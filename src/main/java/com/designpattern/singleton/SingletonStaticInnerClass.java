/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/singleton/SingletonStaticInnerClass.java
 * LastModified:    2020/8/22 下午11:02
 */

package com.designpattern.singleton;

/**
 * 静态内部实现类单例模式
 * @author Jinhua
 * @date 2020/8/22 23:02
 */
public class SingletonStaticInnerClass {

    private SingletonStaticInnerClass(){

    }

    public static SingletonStaticInnerClass getInstance(){
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final SingletonStaticInnerClass sInstance = new SingletonStaticInnerClass();
    }
}
