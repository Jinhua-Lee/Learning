package cn.mythread.consume.obj;

/**
 * 产品的生产者
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/11 22:48
 */
public interface Producer<T> extends Runnable {

    /**
     * 生产产品的方法
     *
     * @return 生产的产品
     */
    T produce();
}
