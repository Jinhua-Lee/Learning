package cn.alg.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * 求一个字符串的最长回文子串。<p>&emsp;
 * 1) babad -> bab / aba<p>&emsp;
 * 2) cbbd -> bb<p>&emsp;
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/2/26 22:52
 */
public class LongestPalindrome {

    public static void main(String[] args) {
    }

    public String longestPalindrome(String s) {
        Map<Character, Integer> char2LastIndex = new HashMap<>();
        int index = 0;
        int maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
//            char2LastIndex.computeIfAbsent()
        }
        return null;
    }
}
