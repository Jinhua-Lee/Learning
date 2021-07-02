package cn.mythread.learn.stopthreaddemo;

class S1 extends StopThreadDemo implements Runnable {

    private final StopThreadDemo stopThreadDemo;

    public S1(StopThreadDemo stopThreadDemo) {
        this.stopThreadDemo = stopThreadDemo;
    }

    @Override
    public void run() {
        stopThreadDemo.code1();
    }
}

class S2 extends StopThreadDemo implements Runnable {

    private final StopThreadDemo stopThreadDemo;

    public S2(StopThreadDemo stopThreadDemo) {
        this.stopThreadDemo = stopThreadDemo;
    }

    @Override
    public void run() {
        stopThreadDemo.code2();
    }
}

/**
 * 测试volatile是否将修改后的值立即写入主存中
 *
 * @author Jinhua
 * @date 2020/8/17 22:38
 */
public class StopThreadDemo {

    /**
     * 通过打开或关闭volatile注释，进行验证
     */
    private volatile boolean stop;

    /**
     * 线程1的执行逻辑
     */
    public void code1() {
        System.out.println("线程1执行开始……");
        // 语句a
        stop = false;
        // 语句b
        while (!stop) {
            System.out.println("do something...");
        }
        System.out.println("线程1执行结束……");
    }

    /**
     * 线程2的执行逻辑
     */
    public void code2() {
        System.out.println("线程2执行开始……");
        // 语句c
        stop = true;
        System.out.println("线程2执行结束……");
    }

    @SuppressWarnings("all")
    public static void main(String[] args) {
        StopThreadDemo stopThreadDemo = new StopThreadDemo();

        Thread t1 = new Thread(new S1(stopThreadDemo), "线程1");
        Thread t2 = new Thread(new S2(stopThreadDemo), "线程2");

        /*
         * 实际分析语句a, b, c的执行顺序，以及stop的值更新到CPU各自高速缓存的取到值的时间
         *      所以分析原子性和结果依赖性，可能的执行情况：
         *      1. 无volatile可能情况分析：
         *          1) 执行顺序 a -> b -> c。
         *              语句a执行，stop=false从缓存刷新到主存；
         *              语句b执行，从缓存读取stop=false，执行若干循环。
         *              语句c执行，值还未立即对线程1可见，b继续执行循环体；当刷新到主存，各个缓存失效，从主存重新读取stop=true，语句b循环结束。
         *          2）执行顺序 a -> c -> b。
         *              语句a执行，stop=false未及时刷新到主存，但线程1中有自己的缓存，语句b可直接取。
         *              语句c执行，stop=true未及时刷新到主存。
         *              所以b执行的时候，可能情况是六种，按时间区间分两类来看
         *                  若语句a的stop=false先刷新到主存，语句c的stop=true后刷新到主存。
         *                      若语句a的值stop=false未刷新到主存：
         *                          语句b首先取的是自己线程1的缓存stop=false，执行若干循环体。
         *                          此时语句a的值stop=false刷新到主存，各线程重新获取，值未变，继续执行循环体。
         *                          此时语句c的值stop=true刷新到主存，各线程重新获取，循环体结束。
         *                      若语句a的值stop=false已经刷新到主存，语句c的值stop=true未刷新到主存：
         *                          语句b执行的时候首先从主存读取stop=false到缓存，执行若干循环。
         *                          此时语句c的值stop=true刷新到主存，各个线程重新获取，循环体结束。
         *                      若语句c的值stop=true已经刷新到主存。
         *                          语句b执行的时候，从主存重新获取，循环体不执行。
         *                  若语句c的stop=true先刷新到主存，语句a的stop=false后刷新到主存。
         *                      若语句c的值stop=true未刷新到主存。
         *                          语句b先取自己线程1缓存中的值a的缓存stop=false，执行若干循环。
         *                          此时语句c的值stop=true刷新到缓主存，各线程重新获取，循环结束。
         *                          此时语句a的值stop=false刷新到主存，各个线程重新获取。
         *                      若语句c的值stop=true已经刷新到主存，语句a的值stop=false未刷新到主存。
         *                          语句b执行时候，先从主存重新获取到stop=true，循环体跳过。
         *                          此时语句a的值stop=false刷新到主存，各个线程重新获取。
         *                      若语句a的值stop=false已经刷新到主存。
         *                          语句b执行的时候，从主存读取stop=false，无限循环。
         *          3）执行顺序 c -> a -> b。
         *              c先赋值stop=false，未及时更新到主存；
         *              a后赋值stop=true，未及时更新到主存；
         *              无volatile保证实时性，两个线程更新stop值前后顺序不一定，所以b执行有两种情况：
         *                  先从主存取到c更新的stop=true，循环体跳过。再从主存读取a更新的值stop=false（此时已经没有针对stop的逻辑）
         *                  先从主存取到a更新的stop=false，执行若干个循环体。再从主存读取c更新的stop=true，循环结束。
         *      2. 有volatile保证更新的值立即可显示到主存，其他线程执行需要从主存重新读取，可能情况：
         *          1）执行顺序 a -> b -> c。此过程相对于无volatile，执行的循环体次数应该更少。
         *              语句a设置变量会将stop=false立即写入主存，所有线程的缓存都失效；
         *              语句b从主存重新读取stop=false，执行循环体；
         *              语句c设置stop=true立即写入主存，线程1中缓存失效，立即从主存获取stop=true，结束循环体。
         *          2）执行顺序 a -> c -> b。
         *              语句a设置stop=false立即刷新到主存；
         *              语句c设置stop=true立即刷新到主存，使得所有CPU的缓存行都失效；
         *              所以语句b中取的值只能是从主存中取到stop=true，循环体不执行。
         *          3）执行顺序 c -> a -> b。
         *              语句c设置stop=true立即刷新到主存。
         *              语句a设置stop=false立即刷新到主存，使得所有CPU的缓存行都失效；
         *              所以语句b中取的值只能是从主存中取到stop=false，循环体一直执行。
         */
        t1.start();
        t2.start();

    }

}
