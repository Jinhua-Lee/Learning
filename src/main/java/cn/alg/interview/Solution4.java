package cn.alg.interview;

/**
 * 快速判断一个数是否是2的n次方
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/31 23:06
 */
public class Solution4 {

    public static void main(String[] args) {
        boolean judge = judge(9);
        System.out.println(judge);
    }

    public static boolean judge(int n) {

        String str = Integer.toBinaryString(n);

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }
}
