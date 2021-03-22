package cn.mythread.learn.stopthreaddemo;

/**
 * @author Jinhua
 * @date 2020/8/17 22:42
 */
public class S2 extends StopThreadDemo implements Runnable {

    private final StopThreadDemo stopThreadDemo;

    public S2(StopThreadDemo stopThreadDemo) {
        this.stopThreadDemo = stopThreadDemo;
    }

    @Override
    public void run() {
        stopThreadDemo.code2();
    }
}
