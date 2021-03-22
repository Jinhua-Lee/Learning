package com.se.mynet;

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
