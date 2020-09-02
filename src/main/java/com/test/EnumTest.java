package com.test;

import java.util.*;

public class EnumTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入尺寸(SMALL)(MEDIUM)(LARGE)(EXTRA_LARGE)");
		String str = sc.next().toUpperCase();
		sc.close();
		Size s = Enum.valueOf(Size.class, str);
		System.out.println("size = " + s);
		System.out.println("abbreviation = " + s.getAbbreviation());
		if(s == Size.EXTRA_LARGE) {
			System.out.println("Good job -- you paid attention to the _.");
		}
	}
}

