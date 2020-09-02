package com.test;

public class Outer {
	public static Inter method() {
		return new Inter() {
			public void show() {
				System.out.println("Hello");
			}
		};
	}
}

interface Inter {
	public void show();
}
class OuterDemo {
	public static void main(String[] args) {
		String s = "Hello";
		Outer.method().show();
	}
}