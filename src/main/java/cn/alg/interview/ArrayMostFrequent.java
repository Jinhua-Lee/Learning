package cn.alg.interview;

import java.util.Objects;

/**
 * 已知数组中【其中一个数字出现的次数，大于数组长度的一半】<p>
 *
 * 思路：<p>&emsp;
 * 1. 不知道有多少种元素，不能定义变量对每一种进行标记。<p>&emsp;
 * 2. 若用Map来实现计数，则时间复杂度平均是O(n * log n)。空间平均 n log n <p>&emsp;
 * 3. 想一想，如果我们遍历的每一轮：<p>&emsp;
 *     a. 保存【结果】记录当前最多的，【权值】记录当前最多值的权值，<p>&emsp;&emsp;
 *     b. 与保存值相同，则增加其权值。<p>&emsp;&emsp;
 *     c. 与保存的值不同，则减小其权值。当权值减到0，则替换为新的值来记录权值。<p>
 * 举例：<p>
 *  1. [4], 3, 4], 4, 2], 4, 5], 4, 7], 4, 1]，将目标值分散，右中括号分别代表了，当数组长度为1，3，5，7的演变：<p>&emsp;
 *     1) 每增加一个元素，一定是要找的目标值（大于数组长度一半）；<p>&emsp;&emsp;
 *     2) 每增加两个元素，则其中一个必定是目标值。<p>
 *  2. 将目标值聚合紧密一些，每次遍历迭代【目标值】 + 【权值】的方式也可以实现。
 *
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/31 10:11
 */
public class ArrayMostFrequent {

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
            // 当次数变为0的时候，置换为1，并且为新的值
            if (nTimes == 0) {
                candidate = arr[i];
                nTimes = 1;
            }
        }
        return candidate;
    }
}
