package com.se.stream;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 模拟序列化和反序列化
 *
 * @author Jinhua
 */
@Getter
@EqualsAndHashCode(of = {"name", "password"})
public final class StudentSerial implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 静态常量
     */
    private static int count = 0;
    private final String name;
    private final String password;

    /**
     * transient关键字标记的属性不进行序列化
     */
    private final transient int transFlag;

    public StudentSerial(String name, String password) {
        System.out.println("进入构造方法");
        this.name = name;
        this.password = password;
        transFlag = -1;
        count++;
    }
}
