package cn.mythread.bank;

import java.util.Arrays;

/**
 * 银行转账与总金额
 *
 * @author Jinhua
 */
public class Bank {
    private final double[] accounts;

    public Bank(int n, double initBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initBalance);
    }

    /**
     * 账户间转账
     *
     * @param from   转出的账户
     * @param to     转到的账户
     * @param amount 转出金额
     */
    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) {
            return;
        }
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf("%10.2f from %d to %d\t", amount, from, to);
        accounts[to] += amount;
        System.out.printf("总额：%10.2f\r\n", getTotalBalance());
    }

    /**
     * 获取银行账户总余额
     *
     * @return 总金额
     */
    public double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    /**
     * 账户数量
     *
     * @return 账户数量
     */
    public int size() {
        return accounts.length;
    }
}