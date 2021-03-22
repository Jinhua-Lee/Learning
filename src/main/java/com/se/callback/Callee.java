package com.se.callback;

/**
 * 被调用者方法的实现
 *
 * @author Jinhua
 */
public class Callee implements CalleeInterface {
    @Override
    public void method() {
        System.out.println("Callee 要执行的方法体");
    }
}
