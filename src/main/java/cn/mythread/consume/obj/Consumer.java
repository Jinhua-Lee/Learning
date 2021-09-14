package cn.mythread.consume.obj;

/**
 * 消费者接口
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/11 22:48
 */
public interface Consumer<T> extends Runnable {

    /**
     * 消费的方法
     *
     * @param t 消费产品
     */
    void consume(T t);
}
