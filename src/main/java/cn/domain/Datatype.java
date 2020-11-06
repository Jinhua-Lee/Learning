/*
 * Copyright (c)    2019/10/17 下午2:54.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/cn/diagram/Datatype.java
 * LastModified:    2019/10/17 下午2:54
 */

package cn.domain;

import lombok.Data;

/**
 * Datatype_id对应的数据类型
 * @author Jinhua
 */
@Data
public class Datatype {


    private Integer datatypeId;

    /**
     * 表示数据类型的字符串，如"string", "int"
     */
    private String datatype;


}
