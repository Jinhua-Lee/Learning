package com.mythread.bank;

import java.util.Scanner;

/**
 * 银行存款测试
 *
 * @author Jinhua
 */
public class BankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static Bank init(int i) {
        LockBank lockBank = new LockBank(NACCOUNTS, INITIAL_BALANCE);
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        SynBank synBank = new SynBank(NACCOUNTS, INITIAL_BALANCE);
        switch (i) {
            case 1:
                return bank;
            case 2:
                return lockBank;
            case 3:
                return synBank;
            default:
                return null;
        }
    }

    public static void execute(Bank bank, int fromAccount) {
        while (true) {
            try {
                double amount = MAX_AMOUNT * Math.random();
                int toAccount = (int) (bank.size() * Math.random());
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
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                execute(bank, fromAccount);
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
