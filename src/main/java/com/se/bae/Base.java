package com.se.bae;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 测试Lombok唯一键比较的父类
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/5/18 14:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Base {

    private Integer id;
    private String name;

    public static void main(String[] args) {

    }
}
