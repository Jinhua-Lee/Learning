package cn.mythread.learn.reorderdemo;

import lombok.SneakyThrows;

class R1 extends ReorderDemo implements Runnable {

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

class R2 extends ReorderDemo implements Runnable {

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

/**
 * 测试指令重排序
 *
 * @author Jinhua
 * @date 2020/8/16 1:00
 */
public class ReorderDemo {
    private Integer context = null;
    private volatile Boolean initialed = null;

    public Integer loadContext() {
        return 3;
    }

    public void code1() {
        System.out.println("线程1执行开始……");
        context = loadContext();
        initialed = true;
        System.out.println("线程1执行完成……");
    }

    @SneakyThrows
    public void code2() {
        System.out.println("线程2执行开始……");
        while (!initialed) {
            Thread.sleep(10);
        }
        System.out.println(++context);
        System.out.println("线程2执行完成……");
    }

    @SneakyThrows
    public static void main(String[] args) {
        ReorderDemo test = new ReorderDemo();
        Thread t1 = new Thread(new R1(test), "线程1");
        Thread t2 = new Thread(new R2(test), "线程2");
        // 先启动线程1再启动线程2，会出现线程2报错的情况，但偶尔也会正常，
        // 将initialed变量设置为volatile类型时，即可解决该问题(但实际运行中未解决该问题)
        t1.start();
        t2.start();
    }
}
