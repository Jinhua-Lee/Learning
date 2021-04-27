package com.se.collection;

import lombok.Data;

import java.time.LocalDate;

/**
 * 两个同类对象组成的实体类
 * @author Jinhua
 */
@Data
class Pair<T> {

	/**
	 * first object
	 */
	private T first;

	/**
	 * second object
	 */
	private T second;

	public Pair(T first, T second) {
		super();
		this.first = first;
		this.second = second;
	}
}

/**
 * 数组最大最小对方法实现
 *
 * @author Jinhua
 */
class ArrayAlg {
	/**
	 * 可比较类型的数组中，找出最小和最大元素组成一对的方法
	 *
	 * @param a   可比较的数组
	 * @param <T> 可比较的类型
	 * @return 返回找到的最大和最小值组成一对的对象
	 */
	public static <T extends Comparable> Pair<T> minmax(T[] a) {
		if (a == null || a.length == 0) {
			return null;
		}
		T min = a[0];
		T max = a[0];
		for (T t : a) {
			if (min.compareTo(t) > 0) {
				min = t;
			} else if (max.compareTo(t) < 0) {
				max = t;
			}
		}
		return new Pair<>(min, max);
	}
}

/**
 * @author Jinhua
 */
public class PairTest {

	public static void main(String[] args) {
		LocalDate[] birthdays =
			{
				LocalDate.of(1906, 12, 9),
				LocalDate.of(1815, 12, 10),
				LocalDate.of(1903, 12, 3),
				LocalDate.of(1910, 6, 22)
			};
		Pair<LocalDate> mm = ArrayAlg.minmax(birthdays);
		System.out.println(mm.getFirst());
		System.out.println(mm.getSecond());
	}
}