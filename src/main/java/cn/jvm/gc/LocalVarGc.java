package cn.jvm.gc;

/**
 * 局部变量的gc：观察buf数组的空间回收情况<p>
 * VM参数：<p>&emsp;
 * -XX:+PrintGCDetails
 *
 * @author Jinhua
 * @date 2021/5/1 21:50
 */
public class LocalVarGc {

    public static void main(String[] args) {
        LocalVarGc gc = new LocalVarGc();
        gc.gc1();
//        gc.gc2();
//        gc.gc3();
//        gc.gc4();
//        gc.gc5();
    }

    public void gc1() {
        byte[] buf = new byte[10 * 1024 * 1024];
        // 不执行GC，在该方法栈，buf还在引用
        System.gc();
    }

    public void gc2() {
        byte[] buf = new byte[10 * 1024 * 1024];
        buf = null;
        // 执行buf空间回收，对象无引用
        System.gc();
    }

    public void gc3() {
        {
            byte[] buf = new byte[10 * 1024 * 1024];
        }
        // 不执行回收，buf为第二个局部变量
        System.gc();
    }

    public void gc4() {
        {
            byte[] buf = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        // 执行buf空间回收。局部变量slot复用，占用索引为1的局部变量
        System.gc();
    }

    public void gc5() {
        gc1();
        // 执行了buf空间回收，方法栈桢弹出，作用域失效
        System.gc();
    }
}
