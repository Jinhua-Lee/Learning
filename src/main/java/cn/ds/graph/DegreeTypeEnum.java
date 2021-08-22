package cn.ds.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 度的类型枚举
 * 度、入度、出度
 *
 * @author Jinhua
 * @version 1.0
 * @date 2020/8/26 11:13
 */
@Getter
@AllArgsConstructor
public enum DegreeTypeEnum {

    /**
     * 度
     */
    DEGREE(0, "度"),

    /**
     * 入度
     */
    IN_DEGREE(1, "入度"),

    /**
     * 出度
     */
    OUT_DEGREE(2, "出度");

    private final int value;
    private final String description;
}
