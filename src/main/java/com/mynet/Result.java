/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/mynet/Result.java
 * LastModified:    2020/11/5 上午12:52
 */

package com.mynet;

import lombok.Data;

/**
 * 结果返回类
 *
 * @author Jinhua
 * @date 2020/11/5 0:52
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;
}
