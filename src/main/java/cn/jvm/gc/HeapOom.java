package cn.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出生成dump文件<p>&emsp;
 * -Xms8M -Xmx8M -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/30 15:34
 */
public class HeapOom {

    private final byte[] bytes = new byte[1024 * 1024];

    @SuppressWarnings("all")
    public static void main(String[] args) {
        List<HeapOom> oList = new ArrayList<>();

        int count = 0;
        try {
            while (true) {
                oList.add(new HeapOom());
                count++;
            }
            // oom发生时，会在当前工程的目录下生成dump文件
        } catch (Throwable ex) {
            System.out.println("count = " + count);
            ex.printStackTrace();
        }
    }
}
