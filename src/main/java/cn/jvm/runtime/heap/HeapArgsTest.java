package cn.jvm.runtime.heap;

/**
 * 测试堆空间的常用JVM参数<p>&emsp;
 * 1. -XX:+PrintFlagsInitial<p>&emsp;&emsp;
 * 查看所有参数的【默认初始值】。
 * <p>
 * 2. -XX:+PrintFlagsFinal<p>&emsp;&emsp;
 * 查看所有参数的【最终值】。
 * <p>
 * 3. -Xms<p>&emsp;&emsp;
 * 【初始堆空间大小】，默认物理内存的 1 / 64。
 * <p>
 * 4. -Xmx<p>&emsp;&emsp;
 * 【最大堆空间大小】，默认为物理内存的 1 / 4。
 * <p>
 * 5. -XX:NewRatio<p>&emsp;&emsp;
 * 配置【新生代与老年代】比例中，新生代为1份时【老年代的份数】。
 * <p>
 * 6. -XX:SurvivorRatio<p>&emsp;&emsp;
 * 配置【新生代中】，Eden与S0 / S1中，S0 / S1为1份时【Eden的份数】。
 * <p>
 * 7. -XX:MaxTenuringThreshold<p>&emsp;&emsp;
 * 设置【晋升老年代】的年龄限制。
 * <p>
 * 8. -XX:+PrintGCDetails<p>&emsp;&emsp;
 * 输出GC处理【详细日志】。<p>&emsp;
 * 1) 输出简要信息：<p>&emsp;&emsp;
 * a. -XX:+PrintGC<p>&emsp;&emsp;
 * b. -verbose:gc
 * <p>
 * 9. -XX:HandlePromotionFailure<p>&emsp;&emsp;
 * 是否设置【空间分配担保】。
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/6 21:24
 */
public class HeapArgsTest {

    public static void main(String[] args) {
    }
}
