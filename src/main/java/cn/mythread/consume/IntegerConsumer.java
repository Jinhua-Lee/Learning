package cn.mythread.consume;

import lombok.extern.slf4j.Slf4j;

/**
 * Integer消费者
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/11 23:34
 */
@Slf4j
public record IntegerConsumer(Container<Integer> container) implements Consumer<Integer> {

    @Override
    public void consume(Integer integer) {
        log.info("consume element [{}]", integer);
    }

    @Override
    @SuppressWarnings("all")
    public void run() {
        while (true) {
            this.consume(container.take());
        }
    }
}
