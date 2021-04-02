package cn.jvm.runtime.heap;

import lombok.SneakyThrows;

/**
 * 1. 定义说明：设置堆空间的大小参数<p>&emsp;
 * 1) -Xms 用来设置【年轻代 + 老年代】的初始内存大小<p>&emsp;&emsp;
 * a. -X 是jvm运行参数<p>&emsp;&emsp;
 * b. ms memory start<p>&emsp;
 * 2) -Xmx 用来设置【年轻代 + 老年代】的最大内存大小<p>&emsp;&emsp;
 * a. mx memory max<p>
 * <p>
 * 2. 默认大小<p>&emsp;
 * 1) 初始内存 = 物理内存 / 64; <p>&emsp;
 * 2) 最大内存 = 物理内存 / 4; <p>
 * <p>
 * 3. 当前验证的手动设置<p>&emsp;
 * 1) -Xms600M;<p>&emsp;
 * 2) -Xmx600M;<p>
 * Tips: 建议生产环境 Xms和Xmx设置为相等的值，避免扩容带来的性能损耗。<p>
 * <p>
 * 4. 查看参数设置结果<p>&emsp;
 * 1) 方式一：通过jps + jstat命令，设置程序睡眠时间；<p>&emsp;
 * 2) 方式二：运行前设置JVM参数 -XX:+PrintGCDetails<p>
 * <p>
 * 5. 结果差异性原因分析<p>&emsp;
 * 1) 设置总内存值 = S0C + S1C + EC + OC;<p>&emsp;
 * 2) 实际用到的总内存值 = (S0C 或 S1C) + EC + OC;
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/2 10:46
 */
public class HeapSizeInitial {

    @SneakyThrows
    public static void main(String[] args) {
        // 堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;

        // JVM视图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms" + initialMemory + "M");
        System.out.println("-Xmx" + maxMemory + "M");

        System.out.println("系统内存大小为：" + initialMemory * 64.0 / 1024 + "G");
        System.out.println("系统内存大小为：" + maxMemory * 4.0 / 1024 + "G");

        Thread.sleep(1000000L);
    }
}
