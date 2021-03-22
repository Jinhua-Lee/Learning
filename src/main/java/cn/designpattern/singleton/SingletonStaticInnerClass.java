package cn.designpattern.singleton;

/**
 * 静态内部实现类单例模式
 *
 * @author Jinhua
 * @date 2020/8/22 23:02
 */
public class SingletonStaticInnerClass implements MySingleton {

    private SingletonStaticInnerClass() {

    }

    public static SingletonStaticInnerClass getInstance() {
        return SingletonHolder.S_INSTANCE;
    }

    @Override
    public void doSomething() {
        System.out.println(this + "\t静态内部类实现单例模式.");
    }

    /**
     * 静态内部类中引用当前对象
     */
    private static class SingletonHolder {
        private static final SingletonStaticInnerClass S_INSTANCE = new SingletonStaticInnerClass();
    }
}
