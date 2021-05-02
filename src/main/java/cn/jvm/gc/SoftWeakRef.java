package cn.jvm.gc;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 软引用与弱引用测试：<p>&emsp;
 * 1) 软引用：内存不足才回收<p>&emsp;
 * 2) 弱引用：发现即回收<p>
 * -Xms10M -Xmx10M
 *
 * @author Jinhua
 * @date 2021/5/2 15:36
 */
public class SoftWeakRef {

    public static void main(String[] args) {
        executeSoft();
        executeWeak();
    }

    private static void executeSoft() {
        // 方式一：
//        SoftReference<User> sUser = new SoftReference<>(new User(1, "name1"));

        // 方式二：
        User user = new User(1, "name1");
        SoftReference<User> sUser = new SoftReference<>(user);
        user = null;

        // gc后，能获取到
        System.gc();
        System.out.println("sUser.get() = " + sUser.get());

        // 尝试让内存紧张
        try {
            byte[] b = new byte[7 * 1024 * 1024];
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            // 内存紧张时候被回收，输出null
            System.out.println(sUser.get());
        }
    }

    private static void executeWeak() {
        WeakReference<User> wUser = new WeakReference<>(new User(2, "name2"));
        System.out.println("wUser.get() = " + wUser.get());
        System.gc();
        // 无论内存是否充足，执行过GC后都会回收
        System.out.println("After GC: ");
        System.out.println("wUser.get() = " + wUser.get());
    }

    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class User {
        private final int id;
        private final String name;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}