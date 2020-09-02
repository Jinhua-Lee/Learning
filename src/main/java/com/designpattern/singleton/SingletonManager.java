/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/singleton/SingletonManager.java
 * LastModified:    2020/8/22 下午11:05
 */

package com.designpattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用容器实现单例模式
 * @author Jinhua
 * @date 2020/8/22 23:05
 */
public class SingletonManager {
    private static Map<String, Object> objMap = new HashMap<>();

    private SingletonManager() {

    }

    public static void registerService(String key, Object instance) {
        if (!objMap.containsKey(key) ) {
            objMap.put(key, instance) ;
        }
    }

    public static Object getService(String key) {
        return objMap.get(key) ;
    }
}
