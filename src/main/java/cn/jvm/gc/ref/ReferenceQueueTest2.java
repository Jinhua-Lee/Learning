package cn.jvm.gc.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/12 11:08
 */
public class ReferenceQueueTest2 {
    private static final ReferenceQueue<byte[]> REF_QUEUE = new ReferenceQueue<>();
    private static final int MB = 1024 * 1024;

    @SuppressWarnings("all")
    public static void main(String[] args) {
        Object value = new Object();

        // 守护线程，检测对象回收状态
        Thread thread = new Thread(ReferenceQueueTest2::refGcDaemon);
        thread.setDaemon(true);
        thread.start();

        int hundred = 100;
        Map<Reference<byte[]>, Object> ref2Obj = new HashMap<>(hundred);

        // 循环，将对象放入Map
        putToMapAndPrintSize(value, hundred, ref2Obj);

        // 统计活对象的数量：
        //  - 用weak时：35个
        countAlive(ref2Obj);
    }

    private static void putToMapAndPrintSize(Object value, int hundred, Map<Reference<byte[]>, Object> ref2Obj) {
        for(int i = 0; i < hundred; i++) {
            byte[] bytes = new byte[MB];
            Reference<byte[]> weakReference = new WeakReference<>(bytes, REF_QUEUE);
            ref2Obj.put(weakReference, value);
        }
        System.out.println("map.size->" + ref2Obj.size());
    }

    private static void countAlive(Map<Reference<byte[]>, Object> map) {
        int aliveNum = 0;
        for (Map.Entry<Reference<byte[]>, Object> entry : map.entrySet()){
            if (entry != null){
                if (entry.getKey().get() != null){
                    aliveNum++;
                }
            }
        }
        System.out.println("100个对象中存活的对象数量：" + aliveNum);
    }

    private static void refGcDaemon() {
        try {
            int n = 0;
            Reference<byte[]> k;
            while ((k = (Reference<byte[]>) REF_QUEUE.remove()) != null) {
                System.out.println((++n) + "回收了:" + k);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
