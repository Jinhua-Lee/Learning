package cn.mythread.demo.bank;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 银行存款 公平锁测试
 *
 * @author Jinhua
 */
public class LockBank extends Bank {

    /**
     * 锁对象，通过公平锁来实现
     */
    private final Lock bankLock;

    /**
     * 上锁的条件，资金足够
     */
    private Condition sufficientFunds;

    public LockBank(int n, double initBalance) {
        super(n, initBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    /**
     * 重写转账方法，通过公平锁来实现同步
     *
     * @param from   转出的账户
     * @param to     转到的账户
     * @param amount 转出金额
     */
    @Override
    public void transfer(int from, int to, double amount) {
        bankLock.lock();
        try {
            super.transfer(from, to, amount);
        } finally {
            bankLock.unlock();
        }
    }

    /**
     * 重写获取总余额功能，公平锁实现
     *
     * @return 银行所有账户的余额
     */
    @Override
    public double getTotalBalance() {
        bankLock.lock();
        try {
            return super.getTotalBalance();
        } finally {
            bankLock.unlock();
        }
    }

}
