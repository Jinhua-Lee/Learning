package com.se.reflect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * Todo
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/2/19 18:42
 */
@Slf4j
public class MethodReflectDemo {

    @SneakyThrows
    public static void main(String[] args) {
        Method method = MyInterface.class.getMethod("func", String.class);
        // 获取声明这个方法的类
        Class<?> mClazz = method.getDeclaringClass();
        System.out.println("mClazz = " + mClazz);
    }
}

interface MyInterface {

    /**
     * 接口方法，用于测试方法反射
     *
     * @param var 入参
     * @return 返回值
     */
    Integer func(String var);
}
