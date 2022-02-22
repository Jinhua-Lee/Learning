package cn.alg.interview;

/**
 * 求一个数组中两个元素差的最大值。
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/26 21:01
 */
public class ArrayMaxDiff {

    public static void main(String[] args) {
        int[] arr = {3, 8, 6, 5, 9};
        Integer maxDiff = getMaxDiff2(arr);
        System.out.println("maxDiff = " + maxDiff);
    }

    /**
     * 这个方法在于复杂度O(i * j)，本来只遍历一遍就可以解决
     *
     * @param arr 给定数组
     * @return 差值
     */
    public static Integer getMaxDiff(int[] arr) {
        // 1. 首先判断数组长度
        if (arr.length < 2) {
            return null;
        }
        // 用于迭代，标记最大值
        Integer max = null;
        // 2. i标识该元素
        for (int i = 1; i < arr.length; i++) {
            // 3. j标识其左边的元素
            for (int j = i - 1; j >= 0; j--) {
                // 差值
                int interval = arr[i] - arr[j];
                if (max == null || interval > max) {
                    max = interval;
                }
            }
        }
        return max;
    }

    /**
     * 只遍历一遍就可以了：<p>&emsp;
     * 记录最小值min，差值r（记录两个点不如记录一个点加一个线段，原因是，线段本身就是影响结果的因素，且驱动着整个过程的进行）
     *
     * @param arr 给定数组
     * @return 最大差值
     */
    public static Integer getMaxDiff2(int[] arr) {
        // 1. 异常情况返回
        if (arr.length < 2) {
            return null;
        }
        // 2. 定义初始变量，【当前最小值】【当前最大差值】
        int min = Math.min(arr[0], arr[1]);
        int maxDiff = Math.abs(arr[0] - arr[1]);

        // 3. 遍历一次求差值
        for (int i = 2; i < arr.length; i++) {
            // 3.1 画图理解，小于最小值时候，将最小值更新，并重新计算差值
            if (arr[i] < min) {
                // 注意一定要先算出差值后，再赋值
                maxDiff += min - arr[i];
                min = arr[i];
            } else if (arr[i] > min + maxDiff) {
                // 3.2 这里相当于大于原来的最大值。
                maxDiff = arr[i] - min;
            }
        }
        return maxDiff;
    }
}
