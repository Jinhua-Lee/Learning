package cn.mythread.demo.bank;

import java.util.Scanner;

/**
 * 银行存款测试
 *
 * @author Jinhua
 */
public class BankTest {
    /**
     * 账户数量
     */
    public static final int ACCOUNT_NUM = 100;

    /**
     * 初始金额
     */
    public static final double INITIAL_BALANCE = 1000;

    /**
     * 最大金额
     */
    public static final double MAX_AMOUNT = 1000;

    /**
     * 每次转账间隔
     */
    public static final int DELAY = 10;

    public static Bank init(int i) {
        switch (i) {
            case 1:
                return new Bank(ACCOUNT_NUM, INITIAL_BALANCE);
            case 2:
                return new LockBank(ACCOUNT_NUM, INITIAL_BALANCE);
            case 3:
                return new SynBank(ACCOUNT_NUM, INITIAL_BALANCE);
            default:
                return null;
        }
    }

    /**
     * 执行转账功能
     *
     * @param bank        银行实例，可以是普通银行，公平锁银行，同步银行
     * @param fromAccount 转出的账户
     */
    public static void execute(Bank bank, int fromAccount) {
        while (true) {
            try {
                // 转账金额在最大金额范围内随机
                double amount = MAX_AMOUNT * Math.random();
                // 转到的账户在所有账户内随机
                int toAccount = (int) (bank.size() * Math.random());
                // 执行转账
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((int) (DELAY * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("请输入选项(1-3)：");
        int j = new Scanner(System.in).nextInt();
        Bank bank = BankTest.init(j);
        for (int i = 0; i < ACCOUNT_NUM; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                execute(bank, fromAccount);
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
