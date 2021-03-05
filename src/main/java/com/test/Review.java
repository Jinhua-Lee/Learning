package com.test;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;

/**
 * 一些杂项
 *
 * @author Jinhua
 */
public class Review {

    @SneakyThrows
    public static void main(String[] args) {
        printSystemProperties();
        printSystemEnv();
    }

    /**
     * 测试包装类equals方法和相等判断
     */
    @SuppressWarnings("all")
    public void wrapperEquals() {
        Integer i1 = 127;
        Integer i2 = 127;
        // true
        System.out.println("(i1 == i2) = " + (i1 == i2));
        // true
        System.out.println("i1.equals(i2) = " + i1.equals(i2));

        Integer i3 = 128;
        Integer i4 = 128;
        // false
        System.out.println("(i3 == i4) = " + (i3 == i4));
        // true
        System.out.println("i3.equals(i4) = " + i3.equals(i4));
    }

    /**
     * 打印系统属性
     */
    public static void printSystemProperties() {
        Set<Map.Entry<Object, Object>> entries = System.getProperties().entrySet();
        entries.forEach(en -> System.out.println("properties:    " + en.getKey() + " ---- " + en.getValue()));
    }

    /**
     * 打印系统环境
     */
    public static void printSystemEnv() {
        Set<Map.Entry<String, String>> envs = System.getenv().entrySet();
        envs.forEach(e -> System.out.println("env:    " + e.getKey() + " ---- " + e.getValue()));
    }

    public void test() {
        double monthCoefficient = getMonthCoefficient(1603152000000L, 1601481600000L);
    }


    /**
     * 获取日偏移系数
     *
     * @param logTime   记录时间
     * @param energyPoi 能耗POI时间
     * @return 日偏移系数
     */
    public double getDayCoefficient(long energyPoi, long logTime) {
        // logTime所在天
        LocalDateTime then = LocalDateTime.ofInstant(Instant.ofEpochMilli(logTime), ZoneId.systemDefault());
        System.out.println("then: " + then);

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalTime nowTime = LocalTime.of(nowDateTime.getHour(), 0);
        LocalDateTime nowHour = LocalDateTime.of(nowDateTime.toLocalDate(), nowTime);
        System.out.println("nowHour: " + nowHour);
        long nowHourStamp = getDefaultTimeStamp(nowHour);

        LocalDateTime thenStartDay = then.toLocalDate().atStartOfDay();
        long thenStartStamp = getDefaultTimeStamp(thenStartDay);
        System.out.println("thenStartDay: " + thenStartDay);
        System.out.println("thenStartStamp = " + thenStartStamp);

        LocalDateTime thenEndDay = then.toLocalDate().plusDays(1).atStartOfDay();
        long thenEndStamp = getDefaultTimeStamp(thenEndDay);
        System.out.println("thenEndDay: " + thenEndDay);
        System.out.println("thenEndStamp = " + thenEndStamp);

        double dayCoefficient = BigDecimal.valueOf((double) (energyPoi - logTime) / (thenEndStamp - thenStartStamp))
                .setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("energyPoi - logTime = " + (energyPoi - logTime));
        System.out.println("thenEndStamp - thenStartStamp = " + (thenEndStamp - thenStartStamp));
        System.out.println(dayCoefficient);
        return dayCoefficient;
    }

    public long getDefaultTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取月偏移系数
     *
     * @param energyPoi 能耗POI
     * @param logTime   记录时间
     * @return 月偏移系数
     */
    public double getMonthCoefficient(long energyPoi, long logTime) {

        LocalDateTime then = LocalDateTime.ofInstant(Instant.ofEpochMilli(logTime), ZoneId.systemDefault());
        System.out.println("then: " + then);

        LocalDate thenStartMonth = LocalDate.of(then.getYear(), then.getMonth(), 1);
        long thenStartStamp = getDefaultTimeStamp(thenStartMonth.atStartOfDay());
        System.out.println("thenStartMonth: " + thenStartMonth);
        System.out.println("thenStartStamp = " + thenStartStamp);


        LocalDate thenEndMonth = thenStartMonth.plusMonths(1);
        long thenEndStamp = getDefaultTimeStamp(thenEndMonth.atStartOfDay());
        System.out.println("thenEndMonth: " + thenEndMonth);
        System.out.println("thenEndStamp = " + thenEndStamp);

        double monthCoefficient = BigDecimal.valueOf(
                (double) (energyPoi - logTime) / (thenEndStamp - thenStartStamp)
        ).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("energyPoi - logTime = " + (energyPoi - logTime));
        System.out.println("thenEndStamp - thenStartStamp = " + (thenEndStamp - thenStartStamp));
        System.out.println(monthCoefficient);

        return monthCoefficient;
    }

    /**
     * 求阶乘
     *
     * @param num 待求阶乘的数
     * @return 返回阶乘
     */
    public BigInteger function(Integer num) {
        if (num == 1) {
            return BigInteger.valueOf(num);
        } else {
            return function(num - 1).multiply(BigInteger.valueOf(num));
        }
    }

    /**
     * 利用Map统计单词
     */
    public void myMapDemo() {
        String s = "aababcabcdabcde";
        char[] chs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>(chs.length);
        for (char ch : chs) {
            // 从map中取值
            Integer i = map.get(ch);
            if (i == null) {
                map.put(ch, 1);
            } else {
                map.put(ch, ++i);
            }
        }
        StringBuilder sb = new StringBuilder();
        Set<Character> set = map.keySet();
        for (Character key : set) {
            Integer value = map.get(key);
            sb.append(key).append("(").append(value).append(")");
        }
        System.out.println(sb.toString());
    }

    /**
     * 求两整数数相除的余数
     *
     * @param x 被除数
     * @param s 除数
     * @return 余数
     */
    public int f(int x, int s) {
        int n = 1;
        int result = 0;
        while (x / n % s != 0) {
            result += x / n % s;
            n *= s;
        }
        return result;
    }

    /**
     * 文件读取
     *
     * @param fileDir      文件路径
     * @param fileEncoding 文件编码
     * @throws IOException IO异常
     */
    public void myFileRead(String fileDir, String fileEncoding) throws IOException {
        Scanner sc = new Scanner(Paths.get(fileDir), fileEncoding);
        List<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }
        sc.close();
        for (String string : list) {
            System.out.println(string);
        }
    }

    @Test
    public void guess() {
        System.out.println("猜数字");
        System.out.println("请输入1-100的数字：");
        int lucky = (int) (Math.random() * 100) + 1;
        Scanner sc = new Scanner(System.in);
        int guess = sc.nextInt();

        while (guess != lucky) {
            if (guess > lucky) {
                System.out.println("猜大了！");
            } else {
                System.out.println("猜小了！");
            }
            guess = sc.nextInt();
        }

        System.out.println("猜对了！！！");
        sc.close();
    }


    /**
     * 五位数百位等于其他四位之和
     */
    public void f1() {
        System.out.println("10000-99999的");
        int a, b, c, d, e;
        for (int i = 10000; i < 100000; i++) {
            a = i / 10000;
            b = i / 1000 % 10;
            c = i / 100 % 10;
            d = i / 10 % 10;
            e = i % 10;
            if (c == a + b + d + e) {
                System.out.print(i);
                System.out.print("\t");
            }
        }
    }

    public void func(int N) {
        for (int i = 3; i <= N; i++) {
            int[] a = new int[i];
            int num = (int) Math.pow(10, i - 1) + 1;
            System.out.print(i + "\t");
            while (num <= Math.pow(10, i)) {
                int sum = 0;
                for (int j = 0; j < i; j++) {
                    a[j] = (int) (num / Math.pow(10, j) % 10);
                }
                for (int j = 0; j < i; j++) {
                    sum = sum + (int) Math.pow(a[j], i);
                }
                if (num == sum) {
                    System.out.print(num + "\t");
                }
                num++;
            }
            System.out.print("\n");
        }
    }

    /**
     * 从0到 N，共n行，每行打印N个 *
     *
     * @param N 行数
     */
    public void f5(int N) {
        int i = 0, j = 0;
        while (i < N) {
            while (j <= i) {
                System.out.print("*");
                j++;
            }
            System.out.println();
            i++;
            j = 0;
        }
    }

    /**
     * 乘法口诀表
     *
     * @param n 口诀表最大值
     */
    public void f6(int n) {
        int i = 1, j = 1;
        while (j <= n) {
            while (i <= n && i <= j) {
                System.out.print(i + " * " + j + " = " + i * j + "    ");
                i++;
            }
            System.out.println();
            i = 1;
            j++;
        }
    }

    public void f7() {
        int day = 0;
        double money = 0;
        while (money < 100) {
            day++;
            money += 2.5;
            if (day % 5 == 0 && money >= 6) {
                money -= 6;
            }
        }
        System.out.println(day);
    }
}

