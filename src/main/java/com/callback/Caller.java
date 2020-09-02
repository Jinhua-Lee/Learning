/*
 * Copyright (c)    2020/1/19 下午2:26.
 * Author:    Jinhua
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/callback/Caller.java
 * LastModified:    2020/1/19 下午2:26
 */

package com.callback;

import java.util.Scanner;

public class Caller {

    public void call(MyCallerInterface myCallerInterface) {
        myCallerInterface.method();
    }

    public static void main(String[] args) {
        Caller caller = new Caller();
        caller.call(new MyCallerInterface() {
            @Override
            public void method() {
                Scanner scanner = new Scanner(System.in);

                System.out.println(scanner.nextInt());
            }
        });
    }
}
