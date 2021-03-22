package com.se.callback;

/**
 * 保证 Callee 有这个方法，Caller 拿到callee的引用，才能执行对应的方法
 *
 * @author Jinhua
 */
public interface CalleeInterface {

    /**
     * Callee 要做的方法
     */
    void method();
}
