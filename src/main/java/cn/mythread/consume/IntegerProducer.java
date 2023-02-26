package cn.mythread.consume;

import lombok.AllArgsConstructor;

import java.util.Random;

/**
 * Integer生产者
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/11 23:29
 */
@AllArgsConstructor
public class IntegerProducer implements Producer<Integer> {

    private final Container<Integer> container;

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
