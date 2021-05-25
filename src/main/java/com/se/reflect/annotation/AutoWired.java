package com.se.reflect.annotation;

import java.lang.annotation.*;

/**
 * 自定义AutoWired注解
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/5/25 20:15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface AutoWired {

}
