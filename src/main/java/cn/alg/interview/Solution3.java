package cn.alg.interview;

import java.util.Objects;

/**
 * 已知数组中【其中一个数字出现的次数，大于数组长度的一半】
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/31 10:11
 */
public class Solution3 {

    public static void main(String[] args) {
        // 思路：两种颜色糖果，找出数量最多的一种颜色，
        // 每次取两个不同的，则取到最后剩下的，即是出现次数最多的。
        Integer[] arr = {2, 3, 4, 3, 4, 4, 4};
        Integer mostFrequentNum = findMostFrequentNum(arr);
        System.out.println("mostFrequentNum = " + mostFrequentNum);
    }

    public static Integer findMostFrequentNum(Integer[] arr) {
        // 保存当前次数最多的值
        Integer candidate = arr[0];
        // 保存次数
        int nTimes = 1;
        for (int i = 1; i < arr.length; i++) {
            if (Objects.equals(arr[i], candidate)) {
                // 如果与保存的值相等，则次数 +1
                nTimes++;
            } else {
                // 不相等，次数 -1
                nTimes--;
            }
            // 当次数变为0的时候，置换为0，并且为新的值
            if (nTimes == 0) {
                candidate = arr[i];
                nTimes = 1;
            }
        }
        return candidate;
    }
}
