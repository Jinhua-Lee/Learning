/*
 * Copyright (c)    2019/11/14 上午11:30.
 * Author:    Jinhua
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/validation/ValidatorTest.java
 * LastModified:    2019/11/14 上午11:30
 */

package com.validation;

import org.junit.Test;

/**
 * @author Jinhua
 */
public class ValidatorTest {

    @Test
    public void test() {
        Student stu = new Student();
        try {
            stu.setAge(-1);
            System.out.println(stu);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
