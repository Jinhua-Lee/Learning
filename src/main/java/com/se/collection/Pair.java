package com.se.collection;

import lombok.Data;

/**
 * 两个同类对象组成的实体类
 * @author Jinhua
 */
@Data
public class Pair<T> {

	/**
	 * first object
	 */
	private T first;

	/**
	 * second object
	 */
	private T second;

	public Pair() {
		first = null;
		second = null;
	}
	public Pair(T first, T second) {
		super();
		this.first = first;
		this.second = second;
	}

}
