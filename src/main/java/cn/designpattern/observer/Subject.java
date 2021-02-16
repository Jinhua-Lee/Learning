/*
 * Copyright (c)    2019/9/26 上午9:21.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/observer_pattern/Subject.java
 * LastModified:    2019/9/26 上午9:21
 */

package cn.designpattern.observer;


/**
 * 抽象被观察者接口，声明了添加、删除、通知观察者的方法
 * <p>
 * 1. 把所有的观察者对象的引用保存于一个集合中，每一个主题都可以有任意数量的观察者。
 * <p>
 * 2. 抽象主题提供一个接口，可以增加和删除观察者角色。一般用一个抽象类和接口来实现。
 *
 * @author Jinhua
 */
public interface Subject {

    /**
     * 注册观察者的方法
     *
     * @param o 要注册的观察者
     */
    void registerObserver(Observer o);

    /**
     * 移除指定观察者的方法
     *
     * @param o 要移除的观察者
     */
    void removeObserver(Observer o);

    /**
     * 通知所有观察者的方法
     */
    void notifyObservers();
}
