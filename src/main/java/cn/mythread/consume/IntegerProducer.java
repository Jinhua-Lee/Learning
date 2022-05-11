package cn.mythread.consume;

import java.util.Random;

/**
 * Integer生产者
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/11 23:29
 */
public record IntegerProducer(Container<Integer> container) implements Producer<Integer> {

    @Override
    public Integer produce() {
        return new Random().nextInt(20);
    }

    @Override
    @SuppressWarnings("all")
    public void run() {
        while (true) {
            this.container.put(this.produce());
        }
    }
}
