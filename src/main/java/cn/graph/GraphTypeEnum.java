package cn.graph;

/**
 * 图的有向和无向类型枚举
 * @author Jinhua
 * @date 2020/8/17 9:43
 * @version 1.1 重命名类
 */
public enum GraphTypeEnum {

    /**
     * 有向图
     */
    Directed(1, "有向图"),

    /**
     * 无向图
     */
    Undirected(2, "无向图");

    /**
     * 枚举值
     */
    Integer value;

    /**
     * 枚举类型
     */
    String typeName;

    GraphTypeEnum(int value, String typeName) {
        this.value = value;
        this.typeName = typeName;
    }

    public Integer getValue() {
        return value;
    }

    public String getTypeName() {
        return typeName;
    }

}
