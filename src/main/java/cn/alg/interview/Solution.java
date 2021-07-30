package cn.alg.interview;

/**
 * Todo
 * 把两个有序的数组合并成一个有序的数组，时间复杂度O(n)
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/28 19:44
 */
public class Solution {

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 8, 9};
        int[] b = {2, 5, 7, 10, 12};

        int[] merge = merge(a, b);
        for (int m : merge) {
            System.out.print(m + "  ");
        }
    }

    public static int[] merge(int[] a, int[] b) {
        // 1. 特殊情况先返回
        if (a.length == 0) {
            return b;
        }
        if (b.length == 0) {
            return a;
        }
        //2. 对于都非空的情况
        // 数组迭代下标
        int i = 0;
        int j = 0;
        // 结果数组
        int k = 0;
        int[] res = new int[a.length + b.length];

        while (i <= a.length && j <= b.length) {
            if (a[i] < b[j]) {
                res[k++] = a[i++];
            } else {
                res[k++] = b[j++];
            }
            if (i == a.length || j == b.length ) {
                break;
            }
        }
        // 对于其中一个数组取完的情况
        while (i < a.length) {
            res[k++] = a[i++];
        }
        while (j < b.length) {
            res[k++] = b[j++];
        }

        return res;
    }
}
