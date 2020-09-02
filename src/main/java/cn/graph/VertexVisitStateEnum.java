package cn.graph;

/**
 * 节点访问状态枚举类
 * @Author Jinhua
 * @Date 2020/8/19 13:38
 * @Version 1.0
 */
public enum VertexVisitStateEnum {
    /**
     * 节点未被访问过
     */
    Unvisited(0, "未被访问"),
    /**
     * 节点被访问过一次
     */
    VisitedOnce(-1, "被访问过一次"),
    /**
     * 节点的所有后代都被访问过
     */
    AllSubNodesVisited(1, "该节点的所有后代都被访问过");

    /**
     * 值枚举
     */
    private final Integer value;
    /**
     * 值描述
     */
    private final String description;

    VertexVisitStateEnum(Integer value, String description) {
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
