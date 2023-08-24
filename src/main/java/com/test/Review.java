package com.test;

import lombok.SneakyThrows;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 一些杂项
 *
 * @author Jinhua
 */
public class Review {

    @SneakyThrows
    public static void main(String[] args) {
        // printSystemProperties();
        // printSystemEnv();
        //
        // testRegexMatch();
        //
        // guess();

        // testSplit();

        // testPrimitiveEq();

        // testFlatMapOrder();

        // \u000d System.out.println("coder Jinhua");
    }

    public static void testPrimitiveEq() {
        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2));
        int val = 1;
        // true
        System.out.println("integers.contains(val) = " + integers.contains(val));
    }

    public static void testFlatMapOrder() {
        List<List<Integer>> multiDimensionalList = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );

        Optional<Integer> firstOpt = multiDimensionalList.stream()
                .flatMap(List::stream)
                .findFirst();

        System.out.println(firstOpt.orElseThrow());

        List<Integer> collect = multiDimensionalList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    private static void testSplit() {
        String str1 = "ab.";
        String str2 = "ab";
        String[] split1 = str1.split("\\.");
        String[] split2 = str2.split("\\.");
        String[] split3 = str2.split("");
        System.out.println();
    }

    private static void testRegexMatch() {
        String str = "DB1201.DBD0";
        String regex = "DB[0-9]+.";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        boolean find = matcher.find();
        System.out.println("find = " + find);
        System.out.println("matcher.group() = " + matcher.group());
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

    /**
     * 求阶乘
     *
     * @param num 待求阶乘的数
     * @return 返回阶乘
     */
    public static BigInteger factorial(Integer num) {
        if (num == 1) {
            return BigInteger.valueOf(num);
        } else {
            return factorial(num - 1).multiply(BigInteger.valueOf(num));
        }
    }

    /**
     * 利用Map统计单词
     */
    public static void countWords() {
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
        System.out.println(sb);
    }

    /**
     * 求两整数数相除的余数
     *
     * @param x 被除数
     * @param s 除数
     * @return 余数
     */
    public static int remainder(int x, int s) {
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
    public static void myFileRead(String fileDir, String fileEncoding) throws IOException {
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

    public static void guess() {
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
    public static void f1() {
        System.out.println("10000-99999的");
        int a, b, c, d, e;
        for (int i = 10_000; i < 100_000; i++) {
            a = i / 10_000;
            b = i / 1_000 % 10;
            c = i / 100 % 10;
            d = i / 10 % 10;
            e = i % 10;
            if (c == a + b + d + e) {
                System.out.print(i);
                System.out.print("\t");
            }
        }
    }

    public static void func(int N) {
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
     * @param rowNumber 行数
     */
    public static void printTriangle(int rowNumber) {
        int i = 0, j = 0;
        while (i < rowNumber) {
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
    public static void multiplicationFormula(int n) {
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

    public static void f7() {
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

