package cn.mythread.learn.reorderdemo;

/**
 * 指令重排序演示
 *
 * @author Jinhua
 * @date 2020/8/17 0:02
 */
public class R1 extends ReorderDemo implements Runnable {

    private ReorderDemo reOrderDemo = null;

    public R1() {
    }

    public R1(ReorderDemo reOrderDemo) {
        this.reOrderDemo = reOrderDemo;
    }

    @Override
    public void run() {
        reOrderDemo.code1();
    }
}
