/*
 * Copyright (c)    2019/9/26 上午9:49.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/observer_pattern/WechatServer.java
 * LastModified:    2019/9/26 上午9:49
 */

package com.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体被观察者，这里表达为微信的公众号服务
 */
public class WechatServer implements Subject {

    /**
     * 用于保存所有观察者的列表
     */
    private List<Observer> observerList = null;

    /**
     * 推送更新的消息保存
     */
    private String message;

    public WechatServer() {
        observerList = new ArrayList<>();
    }

    /**
     * 注册观察者的方法
     * @param o 要注册的观察者
     */
    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    /**
     * 移除指定观察者的方法
     * @param o 要移除的观察者
     */
    @Override
    public void removeObserver(Observer o) {
        if (!observerList.isEmpty()) {
            observerList.remove(o);
        }
    }

    /**
     * 进行遍历，更新所有观察者的信息
     */
    @Override
    public void notifyObservers() {
        for (int i = 0; i < observerList.size(); i++) {
            Observer observer = observerList.get(i);
            observer.update(message);
        }
    }

    /**
     * 设置消息推送
     * @param message 要推送的消息
     */
    public void setMessage(String message) {
        this.message = message;
        System.out.println("微信服务更新消息：" + message);
        notifyObservers();
    }
}
