package cn.mythread.learn.reorderdemo;

/**
 * @author Jinhua
 * @date 2020/8/17 0:05
 */
public class R2 extends ReorderDemo implements Runnable {

    private ReorderDemo reOrderDemo = null;

    public R2() {
    }

    public R2(ReorderDemo reOrderDemo) {
        this.reOrderDemo = reOrderDemo;
    }

    @Override
    public void run() {
        reOrderDemo.code2();
    }
}
