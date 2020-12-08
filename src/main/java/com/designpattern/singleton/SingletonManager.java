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
 * 使用容器管理单例对象
 *
 * @author Jinhua
 * @date 2020/8/22 23:05
 */
public class SingletonManager {

    /**
     * 存储单例对象的 map
     */
    private static final Map<String, Object> OBJ_MAP = new HashMap<>();

    private SingletonManager() {

    }

    /**
     * 注册到 map 中的方法
     * @param key 注册的键
     * @param instance 单实例
     */
    public static void registerService(String key, Object instance) {
        if (!OBJ_MAP.containsKey(key)) {
            OBJ_MAP.put(key, instance);
        }
    }

    /**
     * 获取根据键单实例的服务
     * @param key 键
     * @return 单例
     */
    public static Object getService(String key) {
        return OBJ_MAP.get(key);
    }
}
