/*
 * Copyright (c)    2020/1/19 下午2:29.
 * Author:    Jinhua
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/callback/BeCalled.java
 * LastModified:    2020/1/19 下午2:29
 */

package com.callback;

public class BeCalled implements MyCallerInterface {
    @Override
    public void method() {
        System.out.println("hello");
    }
}
