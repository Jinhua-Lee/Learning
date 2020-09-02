/*
 * Copyright (c)    2019/10/17 下午4:36.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/cn/dto/Result.java
 * LastModified:    2019/10/17 下午4:36
 */

package cn.dto;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
}
