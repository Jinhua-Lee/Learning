package cn.ds.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 节点访问状态枚举类
 *
 * @author Jinhua
 * @version 1.0
 * @date 2020/8/19 13:38
 */
@Getter
@AllArgsConstructor
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

    private final int value;
    private final String description;
}
