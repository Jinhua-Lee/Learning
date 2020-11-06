package cn.graph;

/**
 * 节点在流向图中的位置枚举类
 *
 * @author Jinhua
 * @version 1.1 修改枚举类名称
 * @date 2020/8/21 14:23
 */
public enum VertexPositionEnum {

    /**
     * 开始节点
     */
    StartNode(0, "开始节点"),

    /**
     * 末端节点
     */
    EndNode(1, "末端节点"),

    /**
     * 中间节点
     */
    MiddleNode(2, "中间节点");

    private final Integer value;
    private final String description;

    VertexPositionEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
