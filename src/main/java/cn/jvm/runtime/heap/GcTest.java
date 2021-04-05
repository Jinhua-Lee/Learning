package cn.jvm.runtime.heap;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试【Minor GC】【Major GC】【Full GC】<p>&emsp;
 * 1) 内存设置： -Xms9M -Xmx9M;<p>&emsp;
 * 2) GC日志： -XX:+PrintGCDetails
 *
 * @author Jinhua
 * @date 2021/4/5 17:14
 */
public class GcTest {

    @SneakyThrows
    public static void main(String[] args) {
        int i = 0;

        try {
            List<String> strList = new ArrayList<>();
            String str = "Jinhua-Lee";
            for (; ; ) {
                strList.add(str);
                str = str + str;
                Thread.sleep(20L);
                i++;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("遍历次数为：" + i);
        }
    }
}
