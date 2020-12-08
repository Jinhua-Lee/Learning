package cn.graph;

import java.util.Stack;

/**
 * 记录访问状态及标记是否有环的结果
 * 1. 如果无环，则标记变量为false，栈为空；
 * 2. 如果有环，则标记变量为true，栈为出现环的遍历路径
 *
 * @author Jinhua
 * @version 1.0
 * @date 2020/8/19 15:47
 */
public class VisitStackAndCircle {
    /**
     * 是否有环
     */
    private final Boolean withCircle;

    /**
     * 出现环的访问栈路径
     */
    private final Stack<Vertex<?>> visitStack;

    public VisitStackAndCircle(Boolean withCircle, Stack<Vertex<?>> visitStack) {
        this.withCircle = withCircle;
        this.visitStack = visitStack;
    }

    public Boolean getWithCircle() {
        return withCircle;
    }

    public Stack<Vertex<?>> getVisitStack() {
        return visitStack;
    }
}
