/*
 * Copyright (c)    2020/1/19 下午2:26.
 * Author:    Jinhua
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/callback/CalleeInterface.java
 * LastModified:    2020/1/19 下午2:26
 */

package com.callback;

/**
 * 保证 Callee 有这个方法，Caller 拿到callee的引用，才能执行对应的方法
 *
 * @author Jinhua
 */
public interface CalleeInterface {

    /**
     * Callee 要做的方法
     */
    void method();
}
