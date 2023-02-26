package com.ee.aspectj;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public aspect AccountAspect {

    // 切点定义
    pointcut callPay(int amount, Account account):
            call(boolean com.ee.aspectj.Account.pay(int)) && args(amount) && target(account);

    // 前置通知定义
    before(int amount, Account account): callPay(amount, account) {
        System.out.println("[AccountAspect] 付款前总金额: " + account.getBalance());
        System.out.println("[AccountAspect] 需要付款: " + amount);
    }

    // 环绕通知定义
    boolean around(int amount, Account account): callPay(amount, account) {
        if (account.getBalance() < amount) {
            System.out.println("[AccountAspect]拒绝付款!");
            return false;
        }
        return proceed(amount, account);
    }

    // 后置通知定义
    after(int amount, Account balance): callPay(amount, balance) {
        System.out.println("[AccountAspect] 付款后，剩余：" + balance.getBalance());
    }
}