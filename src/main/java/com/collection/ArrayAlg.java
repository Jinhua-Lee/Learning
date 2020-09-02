package com.collection;

public class ArrayAlg {
	public static <T extends Comparable> Pair<T> minmax(T[] a) {
		if(a == null || a.length == 0) {
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
