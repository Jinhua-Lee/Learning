package cn.jvm.stringtable;

import lombok.SneakyThrows;
import org.springframework.util.StopWatch;

/**
 * String的intern方法空间使用上的效率测试
 * <p>
 * 替换for循环中的语句，通过JVisual VM查看intern的影响
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/28 10:52
 */
public class StringInternEff {
    static final int MAX_COUNT = 1_000 * 10_000;
    static final String[] ARRAYS = new String[MAX_COUNT];

    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {
        Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 0; i < MAX_COUNT; i++) {
//            ARRAYS[i] = new String(String.valueOf(data[i % data.length]));
            ARRAYS[i] = new String(String.valueOf(data[i % data.length])).intern();
        }
        sw.stop();
        System.out.println("花费时间：" + sw.getTotalTimeMillis());

        Thread.sleep(1_000_000L);
        System.gc();
    }
}
