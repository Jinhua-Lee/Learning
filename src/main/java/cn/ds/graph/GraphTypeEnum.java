package cn.ds.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 图的有向和无向类型枚举
 *
 * @author Jinhua
 * @version 1.1 重命名类
 * @date 2020/8/17 9:43
 */
@Getter
@AllArgsConstructor
public enum GraphTypeEnum {

    /**
     * 有向图
     */
    Directed(1, "有向图"),

    /**
     * 无向图
     */
    Undirected(2, "无向图");

    private int value;
    private String typeName;
}
