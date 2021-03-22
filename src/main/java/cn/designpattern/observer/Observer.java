package cn.designpattern.observer;

/**
 * 抽象观察者接口
 * <p>
 * 为所有的具体观察者定义一个接口，在得到主题通知时候更新自己。
 *
 * @author Jinhua
 */
public interface Observer {

    /**
     * 定义更新的方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调
     * <p>
     * 可以在该方法中定义收到消息要做的事
     *
     * @param message 被观察者发出的消息
     */
    void update(String message);
}
