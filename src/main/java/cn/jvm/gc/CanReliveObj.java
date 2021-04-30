package cn.jvm.gc;

import lombok.SneakyThrows;

import java.util.Objects;

/**
 * GC过程，可复活对象的演示<p>
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/30 14:18
 */
public class CanReliveObj {

    public static CanReliveObj obj;

    @SneakyThrows
    public static void main(String[] args) {
        obj = new CanReliveObj();

        // 第一次自救
        obj = null;
        System.gc();
        System.out.println("第一次GC完成！");
        // finalizer优先级低，暂停一下
        Thread.sleep(2_000L);
        String result = Objects.nonNull(obj) ? "第一次自救成功！" : "第一次自救失败...";
        System.out.println(result);

        // 第二次自救
        obj = null;
        System.gc();
        System.out.println("第二次GC完成！");
        // finalizer优先级低，暂停一下
        Thread.sleep(2_000L);
        result = Objects.nonNull(obj) ? "第二次自救成功！" : "第二次自救失败...";
        System.out.println(result);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类的finalize() 方法");
        obj = this;
    }
}
