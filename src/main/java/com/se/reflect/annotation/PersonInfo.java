package com.se.reflect.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * Todo
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/8 23:29
 */
public class PersonInfo {

    public String empty;

    @PersonInfoAnnotation(name = "李金华", languages = {"c++", "java"})
    public String name;

    @SneakyThrows
    public static void main(String[] args) {
        Class<?> cl = Class.forName("com.se.reflect.annotation.PersonInfo");
        Field[] declaredFields = cl.getDeclaredFields();
        PersonInfo personInfo = (PersonInfo) cl.getDeclaredConstructor().newInstance();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(PersonInfoAnnotation.class)) {
                field.setAccessible(true);
                PersonInfoAnnotation annotation = field.getAnnotation(PersonInfoAnnotation.class);
                // 设置字段值为注解上的值
                field.set(personInfo, annotation.name());
            }
        }
        String personInfoStr = new ObjectMapper().writeValueAsString(personInfo);
        System.out.println("personInfoStr = " + personInfoStr);
    }
}
