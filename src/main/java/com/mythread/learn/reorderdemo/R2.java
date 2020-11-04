/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/mythread/learn/reorderdemo/R2.java
 * LastModified:    2020/8/17 上午12:08
 */

package com.mythread.learn.reorderdemo;

/**
 * @author Jinhua
 * @date 2020/8/17 0:05
 */
public class R2 extends ReorderDemo implements Runnable {

    private ReorderDemo reOrderDemo = null;

    public R2() {
    }

    public R2(ReorderDemo reOrderDemo) {
        this.reOrderDemo = reOrderDemo;
    }

    @Override
    public void run() {
        reOrderDemo.code2();
    }
}
