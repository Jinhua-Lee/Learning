package cn.mythread.consume;

import cn.mythread.consume.obj.ObjImplContainer;

import java.util.Random;

/**
 * Integer生产者
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/11 23:29
 */
public class IntegerProducer implements Producer<Integer> {

    /**
     * 生产的产品要放入容器
     */
    private final Container<Integer> container;

    public IntegerProducer(Container<Integer> container) {
        this.container = container;
    }

    @Override
    public Integer produce() {
        return new Random().nextInt(20);
    }

    @Override
    public void run() {
        while (true) {
            this.container.put(this.produce());
        }
    }
}
