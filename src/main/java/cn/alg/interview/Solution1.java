package cn.alg.interview;

/**
 * Todo
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/26 21:01
 */
public class Solution1 {

    public static void main(String[] args) {
        int[] arr = {3, 8, 6, 5, 9};
        Integer maxDif = getMaxDif(arr);
        System.out.println("maxDif = " + maxDif);
    }

    public static Integer getMaxDif(int[] arr) {
        // 1. 首先判断数组长度
        if (arr.length == 0 || arr.length == 1) {
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
}
