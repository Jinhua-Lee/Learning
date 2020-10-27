/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/runtime/stack/dl/DynamicLinkingTest.java
 * LastModified:    2020/10/25 上午12:33
 */

package com.jvm.runtime.stack.dl;

/**
 * 动态链接演示
 * @author Jinhua
 * @date 2020/10/25 0:27
 */
public class DynamicLinkingTest {

    private int num = 10;

    public void methodA() {
        System.out.println("methodA()...");
    }

    public void methodB() {
        System.out.println("methodB()...");
        methodA();
        num++;
    }
}
