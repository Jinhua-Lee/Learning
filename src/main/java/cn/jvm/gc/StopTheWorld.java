package cn.jvm.gc;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * GC时候停止用户线程的测试
 *
 * @author Jinhua
 * @date 2021/5/2 0:11
 */
public class StopTheWorld {

    public static class WorkThread extends Thread {
        // 泛型可以指引用类型，数组也是引用类型
        List<byte[]> bytesList = new ArrayList<>();

        @Override
        @SuppressWarnings("all")
        public void run() {
            while (true) {
                int number = 1_000;
                for (int i = 0; i < number; i++) {
                    bytesList.add(new byte[1024]);
                }
                if (bytesList.size() > 10_000) {
                    bytesList.clear();
                    // 提醒JVM触发Full GC，用户线程PrintThread会挂起
                    System.gc();
                }
            }
        }
    }

    /**
     * 打印信息
     */
    public static class PrintThread extends Thread {
        public static final long ST = System.currentTimeMillis();

        @Override
        @SneakyThrows
        @SuppressWarnings("all")
        public void run() {
            while (true) {
                long dur = System.currentTimeMillis() - ST;
                System.out.println(dur / 1_000 + "." + dur % 1_000);
                Thread.sleep(1_000);
            }
        }
    }

    public static void main(String[] args) {
        PrintThread pt = new PrintThread();
        WorkThread wt = new WorkThread();
        pt.start();
        wt.start();
    }
}
