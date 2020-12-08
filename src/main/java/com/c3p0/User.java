package com.c3p0;

import lombok.Data;
import lombok.ToString;

/**
 * 用户类
 *
 * @author Jinhua
 */
@Data
@ToString
public class User {

    /**
     * 用户Id
     */
    private int ID;

    /**
     * 用户名
     */
    private String Name;

    /**
     * 用户密码
     */
    private String Password;

    /**
     * 用户性别
     */
    private String Sex;

    /**
     * 用户家乡
     */
    private String Home;

    /**
     * 用户备注
     */
    private String Info;
}
