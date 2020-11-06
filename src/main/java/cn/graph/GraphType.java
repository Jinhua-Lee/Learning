package cn.graph;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2020/8/17 9:43
 */
public enum GraphType {

    /**
     * 有向图
     */
    Directed(1, "有向图"),

    /**
     * 无向图
     */
    Undirected(2, "无向图");

    Integer value;
    String typeName;

    GraphType(int value, String typeName) {
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
