package cn.jvm.gc;

/**
 * {@link System#gc()}方法测试
 *
 * @author Jinhua
 * @date 2021/5/1 21:18
 */
public class SystemGc {

    public static void main(String[] args) {
        new SystemGc();
        // 提醒JVM执行gc方法
        System.gc();
        // 加上该方法，一定会执行gc
//        System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("重写了finalize方法");
    }
}
