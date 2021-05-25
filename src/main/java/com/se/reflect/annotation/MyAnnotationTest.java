package com.se.reflect.annotation;

import com.se.reflect.UserController;

import java.util.Arrays;

/**
 * 自定义注解实现反射
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/5/25 20:17
 */
public class MyAnnotationTest {

    public static void main(String[] args) throws Exception {
        Class<UserController> ucClass = UserController.class;
        UserController userController = ucClass.getDeclaredConstructor().newInstance();

        Arrays.stream(ucClass.getDeclaredFields()).forEach(field -> {
            if (field.isAnnotationPresent(AutoWired.class)) {
                field.setAccessible(true);
                // 属性所属类型
                Class<?> type = field.getType();
                try {
                    field.set(userController, type.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(userController.getUserService());
    }
}
