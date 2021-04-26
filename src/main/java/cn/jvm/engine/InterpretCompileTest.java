package cn.jvm.engine;

import org.springframework.util.StopWatch;

/**
 * 解释执行和编译执行，VM参数测试<p>&emsp;
 * 1) 解释执行，-Xint: 7958ms;<p>&emsp;
 * 2) 编译执行，-Xcomp: 1007ms;<p>&emsp;
 * 3) 混合执行，-Xmixed: 1080ms;
 *
 * @author Jinhua
 * @date 2021/4/26 22:10
 */
public class InterpretCompileTest {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        testPrimeNumber(1_000_000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    private static void testPrimeNumber(int count) {
        for (int i = 0; i < count; i++) {
            // 计算100以内质数
            int num = 100;
            // label标签相当于goto，方便多重循环的退出
            label:
            for (int j = 2; j <= num; j++) {
                for (int k = 2; k <= Math.sqrt(j); k++) {
                    if (j % k == 0) {
                        continue label;
                    }
                }
//                System.out.println(j);
            }
        }
    }
}
