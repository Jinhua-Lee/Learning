/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/callback/AnotherCallee.java
 * LastModified:    2020/11/7 上午1:37
 */

package com.callback;

/**
 * 另一个被调用者
 * @author Jinhua
 * @date 2020/11/7 1:37
 */
public class AnotherCallee implements CalleeInterface {

    @Override
    public void method() {
        System.out.println("另一个 Callee 要执行的方法体.");
    }
}
