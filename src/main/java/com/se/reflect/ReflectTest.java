package com.se.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 测试反射注入UserService值
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/5/25 19:50
 */
public class ReflectTest {

    public static void main(String[] args) throws Exception {
        Class<UserController> clazz = UserController.class;
        UserController userController = clazz.getDeclaredConstructor().newInstance();

        UserService userService = new UserService();
        // 获取字段
        Field serviceField = clazz.getDeclaredField("userService");
        serviceField.setAccessible(true);

        String name = serviceField.getName();
        // 获取Setter名
        String setterName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        // 获取Setter
        Method method = clazz.getMethod(setterName, UserService.class);
        // 调用Setter
        method.invoke(userController, userService);

        System.out.println(userController.getUserService());
        System.out.println(userService);
    }
}
