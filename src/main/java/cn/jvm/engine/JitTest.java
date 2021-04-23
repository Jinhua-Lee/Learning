package cn.jvm.engine;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * JIT编译器测试<p>&emsp;
 * 通过Java VisualVM工具，查看编译次数
 *
 * @author Jinhua
 * @date 2021/4/22 23:07
 */
public class JitTest {

    @SneakyThrows
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        int size = 1_000;
        for (int i = 0; i < size; i++) {
            strList.add("让天下没有难学的技术");
            Thread.sleep(1_000L);
        }
        System.out.println(strList.size());
    }
}
