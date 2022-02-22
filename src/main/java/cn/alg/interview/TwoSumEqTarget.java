package cn.alg.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Todo
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/2/23 0:46
 */
public class TwoSumEqTarget {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] indexes = twoSum2(nums, target);
        for (int index : indexes) {
            System.out.println("index = " + index);
        }
    }

    /**
     * 1. 暴力法，双重循环，时间复杂度O(n^2)
     *
     * @param nums   给定数组
     * @param target 给定的和
     * @return 等于和的下标
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 2. 引入HashMap，可以保存【当前的值】->【下标】
     *
     * @param nums   给定数组
     * @param target 给定的和
     * @return 等于和的两个数的下标
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> val2Index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (val2Index.containsKey(target - nums[i])) {
                return new int[]{val2Index.get(target - nums[i]), i};
            }
            val2Index.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
