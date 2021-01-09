/*
 * Copyright (c)    2020/1/19 下午2:26.
 * Author:    Jinhua
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/callback/Caller.java
 * LastModified:    2020/1/19 下午2:26
 */

package com.se.callback;

/**
 * 调用者
 *     关键是要拿到被调用者的引用
 *
 * @author Jinhua
 */
public class Caller {

    /**
     * 调用方法
     * @param calleeInterface 被调用者的引用，以接口来保证它有要执行的方法
     */
    public void call(CalleeInterface calleeInterface) {
        // 执行被调用者的方法
        calleeInterface.method();
    }

    public static void main(String[] args) {
        Caller caller = new Caller();
        // 拿到被调用者的引用，执行被调用者的方法
        // 都是由调用者执行的
        caller.call(new Callee());

        caller.call(new AnotherCallee());

        caller.call(() -> {
            System.out.println("匿名的被调用者要执行的方法");
        });
    }
}
