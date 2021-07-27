package com.interview;

import java.util.Stack;

/**
 * 查找有效子字符串的长度【连续匹配的括号】<p>
 * 核心思路：<p>&emsp;
 * 1. 用栈保存，记录出栈次数；<p>&emsp;
 * 2. 分段统计，两种状态，计数和查找；<p>&emsp;
 * 3. 注意到串尾部仍然是匹配状态，需要将当前的数量更新。<p>&emsp;
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/26 21:19
 */
public class Solution2 {

    public static void main(String[] args) {
        String str = "()()()()";
        int maxLength = maxLengthOfSubStr(str);
        System.out.println("maxLength = " + maxLength);
    }

    public static int maxLengthOfSubStr(String str) {
        // 1. 为空串长度为1，直接返回0
        if (str == null || str.isEmpty() || str.length() == 1) {
            return 0;
        }
        Stack<Character> stack = new Stack<>();
        // 2. 记录出栈次数
        int maxPopNum = 0;
        int curPopNum = 0;
        // 记录是否开始计数
        boolean state = false;
        // 记录当前匹配开始的下标
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // 未开始计数
            if (!state) {
                if (ch == '(') {
                    // 找到了
                    state = true;
                    stack.push(ch);
                }
                // 不为左括号，继续往下找
                continue;
            }
            // 开始计数
            if (ch == '(') {
                stack.push(ch);
            } else {
                if (!stack.isEmpty()) {
                    // 出栈成功，次数 + 1
                    stack.pop();
                    curPopNum++;
                } else {
                    if (curPopNum > maxPopNum) {
                        maxPopNum = curPopNum;
                    }
                    // 当前次数置为0，寻找下一个左括号
                    state = false;
                    curPopNum = 0;
                }
            }
        }
        if (state) {
            if (curPopNum > maxPopNum) {
                maxPopNum = curPopNum;
            }
        }
        return maxPopNum * 2;
    }
}
