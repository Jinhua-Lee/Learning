package cn.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 求给定范围内的所有质数
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/1 16:45
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int low = sc.nextInt();
        int high = sc.nextInt();

        List<Integer> targetList = new ArrayList<>();
        for (int i = low; i < high; i++) {
            if (isZhiShu(i)) {
                targetList.add(i);
            }
        }
        if (targetList.isEmpty()) {
            System.out.println(0);
        }
        // 保存十位、个位之和
        int aSum = 0;
        int bSum = 0;
        for (Integer target : targetList) {
            int a = target % 100 / 10;
            int b = target % 10;
            aSum += a;
            bSum += b;
        }
        System.out.println( Math.max(aSum, bSum));
    }

    public static boolean isZhiShu(int n) {
        if ( n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        double pow = Math.pow(n, 0.5d);

        for (int i = 2; i <= pow; i++) {
            if ( n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
