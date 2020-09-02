package com.sort;

import org.junit.jupiter.api.Test;

/**
 * @author Jinhua
 */
public class MySort {

	@Test
	public void test() {
		int[] a = {32, 43, 23, 13, 5};
//		insertSort(a);
//		bubbleSort(a);
//		quickSort(a, 0, a.length - 1);
		swapSort(a);
		for (int i : a) {
			System.out.println(i);
		}
	}

	/**
	 * 1. 直接插入排序
	 * @param a  待排序数组
	 */
	public void insertSort(int[] a) {
		int length = a.length;
		// 存放待插入数据的变量
		int insertNum;
		// 从第二位开始依次遍历插入
		for (int i = 1; i < length; i++) {
			insertNum = a[i];
			int j = i - 1;
			// 从前往后遍历大于insertNum的数，依次往后移动
			while (j >= 0 && a[j] > insertNum) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = insertNum;
		}
	}

	/**
	 * 2. 冒泡排序
	 * @param a 待排序数组
	 */
	public void bubbleSort(int[] a) {
		int temp;
		for (int i = 1; i < a.length; i++) {
			for (int j = 0; j < a.length - 1; j++) {
				if (a[j] > a[j+1]) {
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
	}

	/**
	 * 3. 简单选择排序
	 * @param a 待排序数组
	 */
	public void selectSort(int[] a) {
		int len = a.length;
		// 循环次数
		for(int i = 0; i < len ; i++){
			int value = a[i];
			int position = i;
			// 找到最小的值和位置
			for(int j = i+1; j < len; j++){
				if(a[j] < value){
					value = a[j];
					position = j;
				}
			}
			// 进行交换
			a[position] = a[i];
			a[i] = value;
		}
	}

	/**
	 * 4. 快速排序
	 * @param a 待排序数组
	 * @param start
	 * @param end
	 */
	public void quickSort(int[] a, int start, int end) {
		if (start < end) {
			// 选基准值
			int baseNum=a[start];
			// 记录中间值
			int midNum;
			int i=start;
			int j=end;
			do {
				while((a[i] < baseNum) && i < end) {
					i++;
				}
				while((a[j] > baseNum) && j > start) {
					j--;
				}
				if(i <= j){
					midNum = a[i];
					a[i] = a[j];
					a[j] = midNum;
					i++;
					j--;
				}
			} while (i<=j);
			if (start < j) {
				quickSort(a, start, j);
			}
			if (end > i){
				quickSort(a, i, end);
			}
		}
	}

	/**
	 * 5. 交换排序
	 * @param a 待排序数组
	 */
	public void swapSort(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
	}
}
