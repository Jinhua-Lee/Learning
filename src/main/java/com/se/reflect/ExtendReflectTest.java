package com.se.reflect;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author Jinhua-Lee
 */
public class ExtendReflectTest {

    static class TypeA {
        protected Integer a;
    }

    static class TypeB extends TypeA {
        private Integer b;
    }

    @SneakyThrows
    public static void main(String[] args) {
        Class<TypeB> bClass = TypeB.class;

        Field[] declaredSuperFields = bClass.getSuperclass().getDeclaredFields();
        Field[] declaredFields = bClass.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(System.out::println);

        TypeB typeB = (TypeB) bClass.getDeclaredConstructors()[0].newInstance();
        declaredFields[0].set(typeB, 2);
        declaredSuperFields[0].set(typeB, 1);

        System.out.println("b.a = " + typeB.a);
    }
}
