package com.se.collection;

import java.time.LocalDate;

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