/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/observer/jdkimpl/ObserverImpl.java
 * LastModified:    2021/2/13 下午6:39
 */

package cn.designpattern.observer.jdkimpl;

import java.util.Observable;
import java.util.Observer;

/**
 * 基于JDK实现观察者
 *
 * @author Jinhua
 * @date 2021/2/13 18:39
 */
public class ObserverImpl implements Observer {

    /**
     * 姓名
     */
    private final String name;

    public ObserverImpl(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        read(o, arg);
    }

    /**
     * 模拟处理消息的方法
     *
     * @param o 被观察者
     */
    private void read(Observable o, Object arg) {
        System.out.println(this.name + " 收到 " + o + " 推送消息： " + arg);
    }

    public static void main(String[] args) {
        Observable subject = new SubjectImpl("demo服务");

        Observer obs1 = new ObserverImpl("观察1");
        Observer obs2 = new ObserverImpl("观察2");
        subject.addObserver(obs1);
        subject.addObserver(obs2);

        subject.notifyObservers("你好，我不爱你了！！！");

    }

}
