/*
 * Copyright (c)    2019/10/17 下午2:49.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/cn/diagram/Property.java
 * LastModified:    2019/10/17 下午2:49
 */

package com.domain;

import lombok.Data;

/**
 * 台账属性列
 *
 * @author Jinhua
 */
@Data
public class Property {

    /**
     * 属性列id
     */
    private Integer propertyId;

    /**
     * 属性列英文名
     */
    private String propertyName;

    /**
     * 属性列中文名
     */
    private String description;

    /**
     * 属性列取值范围
     */
    private String range;

    /**
     * 引用的属性数据类型
     */
    private Datatype datatype;
}
