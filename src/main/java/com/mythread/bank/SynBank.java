package com.mythread.bank;

public class SynBank extends Bank{

	public SynBank(int n, double initBalance) {
		super(n, initBalance);
	}

	@Override
	public synchronized void transfer(int from, int to, double amount) {
		super.transfer(from, to, amount);
	}

	@Override
	public synchronized double getTotalBalance() {
		return super.getTotalBalance();
	}
}
