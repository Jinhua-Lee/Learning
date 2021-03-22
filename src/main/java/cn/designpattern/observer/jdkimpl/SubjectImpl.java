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
