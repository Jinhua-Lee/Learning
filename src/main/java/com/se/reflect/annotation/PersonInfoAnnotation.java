package com.se.reflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Todo
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/8 23:16
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonInfoAnnotation {

    /**
     * 姓名
     *
     * @return 姓名
     */
    String name();

    /**
     * 年龄
     *
     * @return 年龄
     */
    int age() default 18;

    /**
     * 男性
     *
     * @return 默认true
     */
    boolean male() default true;

    /**
     * 开发语言
     *
     * @return 开发语言
     */
    String[] languages();
}
