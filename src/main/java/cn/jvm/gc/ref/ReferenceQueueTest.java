package cn.jvm.gc.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 引用队列测试<p>&emsp;
 * -Xmx50M
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/12 10:19
 */
public class ReferenceQueueTest {

    private static final ReferenceQueue<byte[]> REF_QUEUE = new ReferenceQueue<>();
    private static final int MB = 1024 * 1024;

    static Runnable refQueueDaemonRunnable() {
        return () -> {
            try {
                int cnt = 0;
                Reference<byte[]> k;
                while ((k = (Reference<byte[]>) REF_QUEUE.remove()) != null) {
                    System.out.println((cnt++) + "回收了:" + k);
                }
            } catch (InterruptedException e) {
                //结束循环
            }
        };
    }

    @SuppressWarnings("all")
    public static void main(String[] args) {
        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();

        // 开守护线程，对引用队列中的值进行操作
        Thread thread = new Thread(refQueueDaemonRunnable());
        thread.setDaemon(true);
        thread.start();

        int TEN_THOUSAND = 10_000;
        for (int i = 0; i < TEN_THOUSAND; i++) {
            byte[] bytes = new byte[MB];
            // 软引用，需要指定heap大小才能触发回收
            Reference<byte[]> reference = new SoftReference<>(bytes, REF_QUEUE);
            map.put(reference, value);
        }
        System.out.println("map.size->" + map.size());
    }
}
