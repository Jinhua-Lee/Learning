package cn.mythread.consume;

/**
 * 容器接口
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/14 15:37
 */
public interface Container<T> {

    /**
     * 取出元素的方法
     *
     * @return 取出的元素
     */
    T take();

    /**
     * 存放元素的方法
     *
     * @param t 存放的元素
     */
    void put(T t);
}
