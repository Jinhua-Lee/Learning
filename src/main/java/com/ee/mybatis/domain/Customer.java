/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/mybatis/domain/Customer.java
 * LastModified:    2020/12/26 上午12:13
 */

package com.ee.mybatis.domain;

import lombok.Data;

/**
 * 电商客户
 *
 * @author Jinhua
 * @date 2020/12/26 0:13
 */
@Data
public class Customer {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 客户性别
     */
    private String gender;

    /**
     * 客户年龄
     */
    private Integer age;

    /**
     * 全参构造方法
     *
     * @param id     主键
     * @param name   姓名
     * @param gender 性别
     * @param age    年龄
     */
    public Customer(Integer id, String name, String gender, Integer age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    /**
     * 除去唯一主键的构造方法
     *
     * @param name   姓名
     * @param gender 性别
     * @param age    年龄
     */
    public Customer(String name, String gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
}
