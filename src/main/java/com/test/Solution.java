package com.test;

import org.junit.jupiter.api.Test;

/**
 * @author Jinhua
 */
public class Solution {
	public boolean Find(int target, int [][] array) {
		int i = 0, j = 0;
		int xlength = array.length;
		int ylength = array[0].length;
		if (target < array[0][0] || target > array[xlength - 1][ylength - 1]) {
			return false;
		}
		if (target == array[0][0] || target == array[xlength-1][ylength-1]) {
			return true;
		}
		while (i < xlength - 1 && j < ylength - 1) {
			if (array[i][j] < target) {
				if (array[i][j+1] == target || array[i+1][j] == target) {
					return true;
				}
				if (array[i][j+1] < target) {
					j++;
					continue;
				}
				if (array[i][j+1] > target && array[i+1][j] < target) {
					i++;
				}
			}
		}
		return false;
	}

	/**
	 * 将一个字符串中的每个空格替换成 %20
	 * @param str 源字符串
	 * @return 替换空格后的字符串
	 */
	public String replaceSpace(StringBuffer str) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) != ' ') {
				sb.append(str.charAt(i));
			}
			else {
				sb.append("%20");
			}
		}
		return sb.toString();
	}

	@Test
	public void test() {
		boolean find = Find(5, new int[][]{{2, 3}, {3, 3}, {4, 5}});
		System.out.println(find);
	}
}

