package com.test;

/**
 * 外部方法
 *
 * @author Jinhua
 */
public class Outer {
	public static Inter method() {
		return () -> System.out.println("Hello");
	}
}

/**
 * 内部接口
 */
interface Inter {
	void show();
}
class OuterDemo {
	public static void main(String[] args) {
		String s = "Hello";
		Outer.method().show();
	}
}