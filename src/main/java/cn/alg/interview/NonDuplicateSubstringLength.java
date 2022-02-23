package cn.alg.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/2/23 11:38
 */
public class NonDuplicateSubstringLength {

    public static void main(String[] args) {
        String str1 = "abcabcbb";
        String str2 = "bbbbb";
        String str3 = "pwwkew";
        String str4 = "abba";

        int len1 = lengthOfLongestSubstring(str1);
        int len2 = lengthOfLongestSubstring(str2);
        int len3 = lengthOfLongestSubstring(str3);
        int len4 = lengthOfLongestSubstring(str4);

        System.out.println("len1 = " + len1);
        System.out.println("len2 = " + len2);
        System.out.println("len3 = " + len3);
    }

    public static int lengthOfLongestSubstring(String s) {
        // 记录整个过程中的最长长度
        int maxLen = 0;
        // leftIndex是用来随迭代变的，而不是跟着maxLength一起来保存遍历完成后稳定的。
        int leftIndex = 0;

        // 利用HashMap来记录，每个字符是否出现过，最新下标是多少
        Map<Character, Integer> character2Index = new HashMap<>(s.length());

        for (int i = 0; i < s.length(); i++) {
            // 如果出现过，则将左边界更新为出现过的下一个
            if (character2Index.containsKey(s.charAt(i))) {
                // 为什么要取记录的【left下标】和【Map中记录的当前字符的值的】下一个？
                // 举个例子,abba。
                // 1. 当执行到ab, 下一个字符为b，此时maxLen = 2, leftIndex = 0（记录的是a的下标，而当前记录的是b的下标。
                // 此时要向后滑动，leftIndex应该从上一个s[0]=a的下标0和【s[1]=b的下标1】 + 1中获取，取最新为2没问题。
                // 2. 当执行到abb，下一个字符为a，此时maxLen = 2， leftIndex = 2(第2个b的位置)
                // 此时leftIndex应该从s[2] = b的下标2和已有的a的位置0的下一个中取最大，

                leftIndex = Math.max(leftIndex, character2Index.get(s.charAt(i)) + 1);
            }
            character2Index.put(s.charAt(i), i);
            // 同理做一个长度的大值判断
            maxLen = Math.max(maxLen, i - leftIndex + 1);
        }
        return maxLen;
    }
}
