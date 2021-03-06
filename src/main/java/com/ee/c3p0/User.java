package com.ee.c3p0;

import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Field;

/**
 * 用户类
 *
 * @author Jinhua
 */
@Data
@ToString
public class User {

    /**
     * 用户Id
     */
    private int id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户家乡
     */
    private String home;

    /**
     * 用户备注
     */
    private String info;

    public static void main(String[] args) {

        User user = new User();
        user.setName("ljh");
        user.setId(1);

        Class<? extends User> uClass = user.getClass();

        System.out.println("uClass = " + uClass);

        for (Field field : uClass.getDeclaredFields()) {
            Class<?> fieldType = field.getType();

            if (fieldType.equals(int.class)) {
                // do something
            }

            String name = field.getName();
            System.out.println("fieldType = " + fieldType + "\n name = " + name);
        }

    }
}
