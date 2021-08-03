package cn.alg;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * 某年某月第几周的周几，转换为日期
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/1 15:38
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1. 输入格式：YYYY MM W D，用一个数组来存储
        int[] timeSymbol = new int[4];
        for (int i = 0; i < timeSymbol.length; i++) {
            if (sc.hasNext()) {
                timeSymbol[i] = sc.nextInt();
            }
        }
        // 2. 判断合法性
        if (faultInput(timeSymbol)) {
            System.out.print(0);
        }
        // 先确定给定月份
        LocalDate mStart = LocalDate.of(timeSymbol[0], timeSymbol[1], 1);
        // 确定第一天是星期几
        int firstDayOfWeek = mStart.getDayOfWeek().getValue();
        // 当月天数
        int dayNumOfMonth = getDayNumOfMonth(mStart);
        // 按给定的周数和周几找到所在天，如果超过了当月的天数，则是异常情况

        int day = 1 + (timeSymbol[2] - 1) * 7 + timeSymbol[3] - firstDayOfWeek;
        if (day > dayNumOfMonth) {
            System.out.println(0);
        }
        LocalDate then = LocalDate.of(timeSymbol[0], timeSymbol[1], day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println(formatter.format(then));
    }

    /**
     * 求当月天数
     *
     * @param mStart 月起始日期
     * @return 当月天数
     */
    private static int getDayNumOfMonth(LocalDate mStart) {
        // 当月起始
        LocalDateTime mStartTime = mStart.atStartOfDay();
        long st = mStartTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        // 下月起始
        LocalDateTime nextMonthStart = mStartTime.plusMonths(1L);
        long et = nextMonthStart.toInstant(ZoneOffset.UTC).toEpochMilli();
        // 求当月天数
        long days = (et - st) / (1_000 * 60 * 60 * 24);
        return (int) days;
    }

    /**
     * 判断合法性
     *
     * @param timeSymbol 【年 + 月 + 第几周 + 星期几】的数组
     * @return 输入不合法返回false
     */
    private static boolean faultInput(int[] timeSymbol) {
        if (timeSymbol[1] < 1 || timeSymbol[1] > 12) {
            return true;
        }
        // 周数
        if (timeSymbol[2] < 6) {
            return true;
        }
        if (timeSymbol[3] < 1 || timeSymbol[3] > 7) {
            return true;
        }
        return false;
    }
}
