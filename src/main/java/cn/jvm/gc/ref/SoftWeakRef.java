package cn.jvm.gc.ref;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

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

    private static final ReferenceQueue<User> U_REFER_QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
//        executeSoft();
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
            byte[] buf = new byte[7 * 1024 * 1024];
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            // 内存紧张时候被回收，输出null
            System.out.println(sUser.get());
        }
    }

    @SneakyThrows
    private static void executeWeak() {
        WeakReference<User> wUser = new WeakReference<>(new User(2, "name2"));
        System.out.println("wUser.get() = " + wUser.get());
        System.gc();
        TimeUnit.SECONDS.sleep(5L);
        // 无论内存是否充足，执行过GC后都会回收
        System.out.println("After GC: ");
        System.out.println("wUser.get() = " + wUser.get());
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    private static void executeWeakWithSubClass() {
        // 弱引用 + 引用队列
        WeakReference<User> wUserWithQueue = new UserMain(new User(3, "name3"), U_REFER_QUEUE);
        System.out.println("wUser.get() = " + wUserWithQueue.get());
        System.gc();
        TimeUnit.SECONDS.sleep(5L);
        // 无论内存是否充足，执行过GC后都会回收
        System.out.println("After GC: ");
        System.out.println("wUser.get() = " + wUserWithQueue.get());
        Reference<User> aUser;

        // TODO: 2022/5/11 队列里没有东西，还待调试
        while ((aUser = (Reference<User>) U_REFER_QUEUE.poll()) != null) {
            System.out.println(aUser.get());
        }
    }

    @SuppressWarnings("all")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    static class User {
        private int id;
        private String name;
    }

    @Getter
    static class UserMain extends WeakReference<User> {

        private final Integer userId;

        public UserMain(User weakUser, ReferenceQueue<User> refQueue) {
            super(weakUser, refQueue);
            this.userId = weakUser.id;
        }
    }
}