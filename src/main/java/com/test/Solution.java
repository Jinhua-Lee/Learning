package com.test;

/**
 * something
 *
 * @author Jinhua
 */
public class Solution {

    private static final Solution SOLUTION;

    static {
        SOLUTION = new Solution();
    }

    public static Solution getInstance() {
        return SOLUTION;
    }

    public static void main(String[] args) {
        Solution instance = Solution.getInstance();
//        boolean find = instance.find(5, new int[][]{{2, 3}, {3, 3}, {4, 5}});
//        System.out.println(find);
//        System.out.println(instance.title2Number("ABC"));
        int i = instance.trainingZeros(15);
        System.out.println(i);
    }

    public int trainingZeros(int n) {

        int ans = 0;
        while (n > 0) {
            ans += n / 5;
            n /= 5;
        }
        return ans;
    }

    /**
     * 英文转26进制(Excel的第几列)
     *
     * @param str 英文字符串
     */
    public int title2Number(String str) {

        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            ans = ans * 26 + (c - 'A' + 1);
        }
        return ans;
    }

    public boolean find(int target, int[][] array) {
        int i = 0, j = 0;
        int xLength = array.length;
        int yLength = array[0].length;
        if (target < array[0][0] || target > array[xLength - 1][yLength - 1]) {
            return false;
        }
        if (target == array[0][0] || target == array[xLength - 1][yLength - 1]) {
            return true;
        }
        while (i < xLength - 1 && j < yLength - 1) {
            if (array[i][j] < target) {
                if (array[i][j + 1] == target || array[i + 1][j] == target) {
                    return true;
                }
                if (array[i][j + 1] < target) {
                    j++;
                    continue;
                }
                if (array[i][j + 1] > target && array[i + 1][j] < target) {
                    i++;
                }
            }
        }
        return false;
    }

    /**
     * 将一个字符串中的每个空格替换成 %20
     *
     * @param str 源字符串
     * @return 替换空格后的字符串
     */
    public String replaceSpace(StringBuffer str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                sb.append(str.charAt(i));
            } else {
                sb.append("%20");
            }
        }
        return sb.toString();
    }

}

