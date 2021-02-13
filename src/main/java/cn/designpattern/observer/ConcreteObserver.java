/*
 * Copyright (c)    2019/9/26 上午10:25.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/observer_pattern/ConcreteObserver.java
 * LastModified:    2019/9/26 上午10:25
 */

package cn.designpattern.observer;

/**
 * 实际的观察者
 * <p>
 * 实现观察者所需的更新方法，使得本身状态与试图协调
 *
 * @author Jinhua
 */
public class ConcreteObserver implements Observer {

    /**
     * 姓名
     */
    private final String name;

    /**
     * 消息
     */
    private String message;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + "收到推送消息：" + message);
    }

    public static void main(String[] args) {
        WechatServer ws = new WechatServer();

        Observer o1 = new ConcreteObserver("o1");
        Observer o2 = new ConcreteObserver("o2");
        Observer o3 = new ConcreteObserver("o3");

        ws.registerObserver(o1);
        ws.registerObserver(o2);
        ws.registerObserver(o3);
        ws.setMessage("PHP NB!");

        System.out.println("_____________________");
        ws.removeObserver(o2);
        ws.setMessage("Java !");
    }
}
