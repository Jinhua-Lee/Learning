package com.ee.aspectj;

import lombok.Getter;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2022/11/5 17:03
 */
@Getter
public class Account {

    private int balance = 20;

    public boolean pay(int amount) {
        if (this.balance < amount) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public static void main(String[] args) {
        Account account = new Account();
        account.pay(10);
    }
}

