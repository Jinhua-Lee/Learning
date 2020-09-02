/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/mythread/learn/stopthreaddemo/S2.java
 * LastModified:    2020/8/17 下午10:42
 */

package com.mythread.learn.stopthreaddemo;

/**
 * @author Jinhua
 * @description
 * @date: 2020/8/17 22:42
 */
public class S2 extends StopThreadDemo implements Runnable {

    private StopThreadDemo stopThreadDemo;

    public S2(StopThreadDemo stopThreadDemo) {
        this.stopThreadDemo = stopThreadDemo;
    }

    @Override
    public void run() {
        stopThreadDemo.code2();
    }
}
