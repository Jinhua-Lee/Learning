/*
 * Copyright (c)    2019/9/26 上午9:21.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/observer_pattern/Subject.java
 * LastModified:    2019/9/26 上午9:21
 */

package com.designpattern.observer;


/**
 * 抽象被观察者接口，声明了添加、删除、通知观察者的方法
 */
public interface Subject {

    /**
     * 注册观察者的方法
     * @param o 要注册的观察者
     */
    public void registerObserver(Observer o);

    /**
     * 移除指定观察者的方法
     * @param o 要移除的观察者
     */
    public void removeObserver(Observer o);

    /**
     * 通知所有观察者的方法
     */
    public void notifyObservers();
}
