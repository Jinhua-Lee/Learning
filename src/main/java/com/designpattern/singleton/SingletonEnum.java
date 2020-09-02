/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/singleton/SingletonEnum.java
 * LastModified:    2020/8/22 下午11:03
 */

package com.designpattern.singleton;

import java.io.ObjectStreamException;

/**
 * 枚举实现单例
 * @author Jinhua
 * @date 2020/8/22 23:03
 */
public enum SingletonEnum implements MySingleton {
    /**
     * 单例的枚举
     */
    INSTANCE;

    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

    @Override
    public void doSomething() {

    }

    public static MySingleton getInstance() {
        return SingletonEnum.INSTANCE;
    }
}
