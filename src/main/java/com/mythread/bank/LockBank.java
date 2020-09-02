package com.mythread.bank;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBank extends Bank{
	private Lock bankLock;
	private Condition sufficientFunds;

	public LockBank(int n, double initBalance) {
		super(n, initBalance);
		bankLock = new ReentrantLock();
		sufficientFunds = bankLock.newCondition();
	}

	public void transfer(int from, int to, double amount) {
		bankLock.lock();
		try {
			super.transfer(from, to, amount);
		}
		finally {
			bankLock.unlock();
		}
	}

	public double getTotalBalance() {
		bankLock.lock();
		try {
			return super.getTotalBalance();
		}
		finally {
			bankLock.unlock();
		}
	}

}
