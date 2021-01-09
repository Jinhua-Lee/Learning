/*
 * Copyright (c)    2020/1/19 下午2:29.
 * Author:    Jinhua
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/callback/Callee.java
 * LastModified:    2020/1/19 下午2:29
 */

package com.se.callback;

/**
 * 被调用者方法的实现
 *
 * @author Jinhua
 */
public class Callee implements CalleeInterface {
    @Override
    public void method() {
        System.out.println("Callee 要执行的方法体");
    }
}
