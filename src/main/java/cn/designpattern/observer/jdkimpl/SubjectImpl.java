/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/observer/jdkimpl/SubjectImpl.java
 * LastModified:    2021/2/13 下午6:41
 */

package cn.designpattern.observer.jdkimpl;

import java.util.Observable;

/**
 * 基于JDK实现被观察者
 *
 * @author Jinhua
 * @date 2021/2/13 18:41
 */
public class SubjectImpl extends Observable {

    /**
     * 被观察者名
     */
    private String name;

    public SubjectImpl(String name) {
        this.name = name;
    }

    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg);
    }
}
