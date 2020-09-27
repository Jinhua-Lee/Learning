package com.test;

import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Jinhua
 */
public class Review {

    public static void main(String[] args) {

    }

    @Test
    public void test() {
//		System.out.println(getDayCoefficient(1600153200000L));

//        LocalDate today = LocalDate.now();
//        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
//        System.out.println(lastDay.getDayOfMonth());

//		System.out.println(getMonthCoefficient(1600153200000L));

        double activePower = 807.0;
        double powerFactor = 230.0;
        double ratedCapacity = 20.0;
        double loadRateValue = BigDecimal.valueOf(activePower / (powerFactor * ratedCapacity)
        ).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();

        System.out.println(loadRateValue);
    }


    /**
     * 获取日偏移系数
     * @param logTime
     * @return
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

        LocalDateTime thenEndDay = then.toLocalDate().plusDays(1).atStartOfDay();
        long thenEndStamp = getDefaultTimeStamp(thenEndDay);
        System.out.println("thenEndDay: " + thenEndDay);

        double dayCoefficient = BigDecimal.valueOf((double) (energyPoi - logTime) / (thenEndStamp - thenStartStamp))
                .setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(dayCoefficient);
        return dayCoefficient;
    }

    public long getDefaultTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取月偏移系数
     * @param logTime
     * @return
     */
    public double getMonthCoefficient(long energyPoi, long logTime) {

        LocalDateTime then = LocalDateTime.ofInstant(Instant.ofEpochMilli(logTime), ZoneId.systemDefault());
        System.out.println("then: " + then);

        LocalDate thenStartMonth = LocalDate.of(then.getYear(), then.getMonth(), 1);
        long thenStartStamp = getDefaultTimeStamp(thenStartMonth.atStartOfDay());
        System.out.println("thenStartMonth: " + thenStartMonth);


        LocalDate thenEndMonth = thenStartMonth.plusMonths(1);
        long thenEndStamp = getDefaultTimeStamp(thenEndMonth.atStartOfDay());
        System.out.println("thenEndMonth: " + thenEndMonth);

        double monthCoefficient = BigDecimal.valueOf(
                (double) ( energyPoi - logTime) / (thenEndStamp - thenStartStamp)
        ).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(monthCoefficient);

        return monthCoefficient;
    }

    /**
     * 求阶乘
     * @param num 待求阶乘的数
     * @return 返回阶乘
     */
    public BigInteger function(Integer num) {
        if (num == 1) {
            return BigInteger.valueOf(num);
        }
        else {
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
        for(char ch: chs) {
            // 从map中取值
            Integer i = map.get(ch);
            if(i == null) {
                map.put(ch, 1);
            }
            else {
                map.put(ch, ++i);
            }
        }
        StringBuffer sb = new StringBuffer();
        Set<Character> set = map.keySet();
        for (Character key : set) {
            Integer value = map.get(key);
            sb.append(key).append("(").append(value).append(")");
        }
        System.out.println(sb.toString());
    }

    /**
     * 集合与流操作
     * @param s
     * @return
     */
    public Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            result.add(s.substring(i, i + 1));
        }
        return result.stream();
    }

    /**
     * 求两整数数相除的余数
     * @param x
     * @param s
     * @return
     */
    public int f(int x, int s){
        int n = 1;
        int result = 0;
        while(x / n % s != 0){
            result += x / n % s;
            n *= s;
        }
        return result;
    }

    /**
     * 文件读取
     * @param fileDir 文件路径
     * @param fileEncoding 文件编码
     * @throws IOException
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

    public void listTest2() {
        System.out.println("????");
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while(true) {
            int temp = sc.nextInt();
            if(temp == 0) {
                break;
            }
            list.add(temp);
        }
        sc.close();
        System.out.println(Collections.max(list));
    }

    public void listTest() {
        List<Double> list = new ArrayList(10);
        Random r = new Random();
        for(int i =0; i < 10; i++) {
            list.add(r.nextDouble() * 20);
        }

        Iterator<Double> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void guess() {
        System.out.println("猜数字");
        System.out.println("请输入1-100的数字：");
        int lucky = (int)(Math.random() * 100) + 1;
        Scanner sc = new Scanner(System.in);
        int guess = sc.nextInt();

        while(guess != lucky) {
            if (guess > lucky) {
                System.out.println("猜大了！");
            }
            else {
                System.out.println("猜小了！");
            }
            guess = sc.nextInt();
        }

        System.out.println("猜对了！！！");
        sc.close();
    }


    // 五位数百位等于其他四位之和
    public void f1() {
        System.out.println("10000-99999的");
        int a, b, c, d, e;
        for (int i = 10000; i < 100000; i++) {
            a = i / 10000;
            b = i / 1000 % 10;
            c = i / 100 %  10;
            d = i / 10 % 10;
            e = i % 10;
            if(c == a + b + d + e) {
                System.out.print(i);
                System.out.print("\t");
            }
        }
    }

    public void func(int N) {
        for (int i = 3; i <= N; i++) {
            int a[] = new int[i];
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

    public void f5(int N) {
        int i = 0, j = 0;
        while(i < N) {
            while(j <= i) {
                System.out.print("*");
                j++;
            }
            System.out.println();
            i++;
            j = 0;
        }
    }

    public void f6(int n) {
        int i = 1, j = 1;
        while(j <= n) {
            while(i <= n && i <= j) {
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
        while(money < 100) {
            day++;
            money += 2.5;
            if(day % 5 == 0 && money >= 6) {
                money -= 6;
            }
        }
        System.out.println(day);
    }
}

