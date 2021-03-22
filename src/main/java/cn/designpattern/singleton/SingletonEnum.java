package cn.designpattern.singleton;

import java.io.ObjectStreamException;

/**
 * 枚举实现单例
 *
 * @author Jinhua
 * @date 2020/8/22 23:03
 */
public enum SingletonEnum implements MySingleton {
    /**
     * 单例的枚举
     */
    INSTANCE;

    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

    @Override
    public void doSomething() {
        System.out.println(this + "\t枚举单例模式.");
    }

    public static MySingleton getInstance() {
        return SingletonEnum.INSTANCE;
    }
}
