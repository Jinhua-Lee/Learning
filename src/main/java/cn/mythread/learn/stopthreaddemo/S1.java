package cn.mythread.learn.stopthreaddemo;

/**
 * @author Jinhua
 * @date 2020/8/17 22:41
 */
public class S1 extends StopThreadDemo implements Runnable {

    private final StopThreadDemo stopThreadDemo;

    public S1(StopThreadDemo stopThreadDemo) {
        this.stopThreadDemo = stopThreadDemo;
    }

    @Override
    public void run() {
        stopThreadDemo.code1();
    }
}
