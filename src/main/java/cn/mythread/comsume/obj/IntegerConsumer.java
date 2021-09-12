package cn.mythread.comsume.obj;

import lombok.extern.slf4j.Slf4j;

/**
 * Integer消费者
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/11 23:34
 */
@Slf4j
public class IntegerConsumer implements Consumer<Integer> {

    /**
     * 从容器中取产品
     */
    private final Container<Integer> container;

    public IntegerConsumer(Container<Integer> container) {
        this.container = container;
    }

    @Override
    public void consume(Integer integer) {
        log.info("consume element [{}]", integer);
    }

    @Override
    public void run() {
        while (true) {
            this.consume(container.take());
        }
    }
}
