/*
 * Copyright (c)    2019/9/26 上午9:25.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/observer_pattern/Observer.java
 * LastModified:    2019/9/26 上午9:25
 */

package com.designpattern.observer;

/**
 * 抽象观察者接口
 *
 * @author Jinhua
 */
public interface Observer {

    /**
     * 定义更新的方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调
     *
     * @param message 被观察者发出的消息
     */
    void update(String message);
}
