package cn.alg.interview;

/**
 * 把两个有序的数组合并成一个有序的数组，时间复杂度O(n)<p>
 * 思路：<p>&emsp;
 *  1. 定义一个新数组用来存放结果。<p>&emsp;
 *  2. 由于原数组的有序性，可以直接顺序遍历。比较过程中将较小的元素放入新数组中。<p>&emsp;
 *  3. 当一个数组遍历完成后，需要退出循环，将另一个数组的所有元素都加入到新数组中。<p>
 * 发散：<p>&emsp;
 *  1) 合并多个有序数组。
 *      a. 可以将其当作一个List<int[]>的二维数组；
 *      b. 退出循环的条件即是只剩一个数组，某一个或某几个数组遍历完，则剩下的比较不纳入它。
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/28 19:44
 */
public class MergeTwoSortedArray {

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 8, 9};
        int[] b = {2, 5, 7, 10, 12};

        int[] merge = merge(a, b);
        for (int m : merge) {
            System.out.print(m + "  ");
        }
    }

    public static int[] merge(int[] a, int[] b) {
        // 1. 若其中一个为空，则返回
        if (a.length == 0) {
            return b;
        }
        if (b.length == 0) {
            return a;
        }
        //2. 为两个有序数组定义两个迭代下标，从前向后遍历，把较小的数加入到新数组中。
        // 数组迭代下标
        int i = 0;
        int j = 0;
        // 结果数组迭代下标
        int k = 0;
        int[] res = new int[a.length + b.length];

        while (i <= a.length && j <= b.length) {
            // 按照大小，将较小的数加入到新数组中
            if (a[i] < b[j]) {
                res[k++] = a[i++];
            } else {
                res[k++] = b[j++];
            }
            // 如果有一个数组，遍历完了，则退出循环，并在循环外把另一个数组剩下的元素都放入新数组
            if (i == a.length || j == b.length ) {
                break;
            }
        }
        // 3. 只剩一个数组时候，将其剩余的元素都放入数组末尾。
        while (i < a.length) {
            res[k++] = a[i++];
        }
        while (j < b.length) {
            res[k++] = b[j++];
        }

        return res;
    }
}
