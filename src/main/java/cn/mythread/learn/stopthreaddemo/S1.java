/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/mythread/learn/stopthreaddemo/S1.java
 * LastModified:    2020/8/17 下午10:41
 */

package cn.mythread.learn.stopthreaddemo;

/**
 * @author Jinhua
 * @date 2020/8/17 22:41
 */
public class S1 extends StopThreadDemo implements Runnable {

    private final StopThreadDemo stopThreadDemo;

    public S1(StopThreadDemo stopThreadDemo) {
        this.stopThreadDemo = stopThreadDemo;
    }

    @Override
    public void run() {
        stopThreadDemo.code1();
    }
}
