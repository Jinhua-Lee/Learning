package com.collection;

public class PairAlg {
	public static boolean hasNulls(Pair<?> p) {
		return p.getFirst() == null || p.getSecond() == null;
	}

	public static <T> void  swap(Pair<T> p) {
		T t =p.getFirst();
		p.setFirst(p.getSecond());
		p.setSecond(t);
	}
}
