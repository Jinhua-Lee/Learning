package cn.jvm.gc;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * dump文件生成，测试GC Roots
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/30 14:42
 */
public class GcRoots {

    @SneakyThrows
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        int num = 100;
        for (int i = 0; i < num; i++) {
            numbers.add(i);
            Thread.sleep(10L);
        }
        System.out.println("数据添加完毕，请操作：");
        // 保存堆dump
        new Scanner(System.in).next();
        numbers = null;
        now = null;

        System.out.println("已置空，请操作：");
        // 保存堆dump
        new Scanner(System.in).next();

        System.out.println("结束");
    }
}
