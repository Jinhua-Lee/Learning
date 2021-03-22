package cn.designpattern.singleton;

/**
 * 饿汉式实现单例模式
 *
 * @author Jinhua
 * @date 2020/8/22 22:47
 */
public class SingletonHungry implements MySingleton {

    /**
     * 类加载准备阶段的时候就进行初始化
     */
    private static final SingletonHungry INSTANCE = new SingletonHungry();

    private SingletonHungry() {

    }

    public static SingletonHungry getInstance() {
        return INSTANCE;
    }

    @Override
    public void doSomething() {
        System.out.println(this + "\t饿汉实现单例模式");
    }
}
