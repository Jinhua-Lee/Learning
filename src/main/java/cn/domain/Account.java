/*
 * Copyright (c)    2019/10/17 下午2:46.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/cn/diagram/Account.java
 * LastModified:    2019/10/17 下午2:46
 */

package cn.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * 台账信息表，存储台账的名称和版本
 * @author Jinhua
 */
@Data
public class Account {
    /**
     * 台账的id
     */
    private Integer accountId;

    /**
     * 台账英文名
     */
    private String accountName;

    /**
     * 台账中文名
     */
    private String description;

    /**
     * 台账的信息版本
     */
    private Integer version;

    /**
     * 存储台账所包含的属性列
     */
    private Set<Property> properties = new HashSet<>();

}
