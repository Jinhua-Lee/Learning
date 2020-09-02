package com.mythread.bank;

import java.util.Arrays;

public class Bank {
	private final double[] accounts;

	public Bank(int n, double initBalance) {
		accounts = new double[n];
		Arrays.fill(accounts, initBalance);
	}

	public void transfer(int from, int to, double amount) {
		if (accounts[from] < amount)
			return;
		System.out.println(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf("%10.2f from %d to %d\t", amount, from, to);
		accounts[to] +=amount;
		System.out.printf("总额：%10.2f\r\n", getTotalBalance());
	}

	public double getTotalBalance() {
		double sum = 0;
		for (double a : accounts) {
			sum += a;
		}
		return sum;
	}

	public int size() {
		return accounts.length;
	}
}