package com.mythread.bank;

/**
 * 同步银行
 *
 * @author Jinhua
 */
public class SynBank extends Bank {

    public SynBank(int n, double initBalance) {
        super(n, initBalance);
    }

    /**
     * 重写转账功能，通过同步方法实现
     *
     * @param from   转出的账户
     * @param to     转到的账户
     * @param amount 转出金额
     */
    @Override
    public synchronized void transfer(int from, int to, double amount) {
        super.transfer(from, to, amount);
    }

    /**
     * 重写获取银行所有账户的余额功能，通过同步函数实现
     * @return
     */
    @Override
    public synchronized double getTotalBalance() {
        return super.getTotalBalance();
    }
}
