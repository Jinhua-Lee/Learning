package com.test;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 打招呼类
 */
abstract class Greeter {
    public void greet() {
        System.out.println("Hello World!");
    }
}

/**
 * 定时打招呼类
 */
class TimeGreeter extends Greeter {
    @Override
    public void greet() {
        Timer t = new Timer(1000, (event) -> super.greet());
        t.start();
    }
}

/**
 * lambda 语句测试
 *
 * @author Jinhua
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Santurn", "Uranus", "Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("按名字排序：");
        // 快速排序法
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));

        System.out.println("按长度排序：");
        Arrays.sort(planets, Comparator.comparingInt(String::length));
        System.out.println(Arrays.toString(planets));

        Timer t = new Timer(1000, event -> {
            System.out.println("时间：" + LocalDateTime.now());
            Toolkit.getDefaultToolkit().beep();
        });
        t.start();

        TimeGreeter tg = new TimeGreeter();
        tg.greet();
        JOptionPane.showMessageDialog(null, "Quit program?");
    }
}
