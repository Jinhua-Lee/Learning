package cn.ds.graph;

import lombok.Getter;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 深度优先遍历基本信息及执行类
 *
 * @author Jinhua
 * @version 1.2
 * 更新内容：
 * 兼容外部传参作为开始节点，增加对应构造方法；提供所有属性的Getter；
 * 优化访问状态枚举；
 * 实现判断构成环的逻辑。
 * @date 2020/8/17 15:17
 */
@Getter
public class DepthFirstSearch {

    /**
     * 标记是否被访问的映射
     * 标记枚举值：0：未被访问；-1：被访问过一次；1：所有后代都被访问
     */
    private final Map<Vertex<?>, VertexVisitStateEnum> visited;

    /**
     * 要进行深度优先遍历的有向图
     */
    private final MyGraph graph;

    /**
     * 开始遍历的节点下标
     */
    private Integer start = 0;

    /**
     * 已经访问过的节点数组
     */
    private final List<Vertex<?>> visitedVertices;

    /**
     * 判断是否有环的结果存储类
     */
    private VisitStackAndCircle withCircle;

    public DepthFirstSearch(MyGraph g) {
        this.graph = g;
        this.visited = new HashMap<>();
        this.withCircle = new VisitStackAndCircle(false, null);
        this.visitedVertices = new ArrayList<>();
        for (Vertex<?> vertex : g.getVertices()) {
            visited.put(vertex, VertexVisitStateEnum.Unvisited);
        }
    }

    public DepthFirstSearch(MyGraph g, Integer start) {
        this(g);
        this.start = start;
    }

    public void executeDfs(Consumer<Vertex<?>> userVisit) {
        dfs(this.start, userVisit);
        for (Vertex<?> vertex : visited.keySet()) {
            if (VertexVisitStateEnum.Unvisited.equals(visited.get(vertex))) {
                dfs(vertex.getIndex(), userVisit);
            }
        }
    }

    /**
     * 对指定的图进行遍历，收集叶子节点
     */
    public Collection<Vertex<?>> collectLeafByDfs(Vertex<?> vertex, Supplier<Collection<Vertex<?>>> leafCollectionSupplier) {
        Collection<Vertex<?>> leafCollection = leafCollectionSupplier.get();
        Integer index = this.graph.indexOfVertex(vertex);
        if (index == null) {
            return leafCollection;
        }
        collectLeafByDfs(index, leafCollection);
        return leafCollection;
    }

    private void dfs(int v, Consumer<Vertex<?>> userVisit) {
        // 访问并标记访问状态
        Vertex<?> cur = this.graph.getVertices()[v];
        visit(cur, userVisit);
        visited.put(cur, VertexVisitStateEnum.VisitedOnce);

        // 对每个结点深度优先搜索
        // 获取【直接后代】列表
        for (Vertex<?> vertex : this.graph.getRelatedVertices(v)) {
            if (VertexVisitStateEnum.Unvisited.equals(visited.get(vertex))) {
                dfs(vertex.getIndex(), userVisit);
            }
        }
        visited.put(cur, VertexVisitStateEnum.AllSubNodesVisited);
    }

    private void collectLeafByDfs(int v, Collection<Vertex<?>> leafCollect) {
        // 访问并标记访问状态
        Vertex<?> cur = this.graph.getVertices()[v];
        visit(cur, leafCollect);
        visited.put(cur, VertexVisitStateEnum.VisitedOnce);
        // 对每个结点深度优先搜索
        // 获取【直接后代】列表
        for (Vertex<?> vertex : this.graph.getRelatedVertices(v)) {
            if (VertexVisitStateEnum.Unvisited.equals(visited.get(vertex))) {
                collectLeafByDfs(vertex.getIndex(), leafCollect);
            }
        }
        visited.put(cur, VertexVisitStateEnum.AllSubNodesVisited);
    }

    /**
     * 提供给外部判断是否有环的方法
     * 此处提供有向图判断，无向图可扩展
     *
     * @return 是否有环
     */
    public VisitStackAndCircle judgeWithCircle() {
        Stack<Vertex<?>> visitStack = new Stack<>();
        improvedDfs(start, visitStack);
        for (Vertex<?> vertex : visited.keySet()) {
            if (VertexVisitStateEnum.Unvisited.equals(visited.get(vertex))) {
                improvedDfs(vertex.getIndex(), visitStack);
            }
        }
        return withCircle;
    }

    /**
     * 改进的dfs，判断有向图是否有环
     * 如果存在环，则更新标记字段，记录路径并退出
     * 判断逻辑是：对于当前访问节点到关联节点的边的状态作判断（状态在枚举类中给出）
     * 如果第一次访问（u, v）时，v被访问过一次，则（u,v）为反向边，该图存在环
     *
     * @param v 顶点
     */
    private void improvedDfs(int v, Stack<Vertex<?>> visitStack) {
        Vertex<?> currentVertex = this.graph.getVertices()[v];

        // 用于存：当前节点的所有流出节点的迭代
        Vertex<?> currentRelatedVertex;
        // 执行访问，则执行压栈，更新访问状态
        visit(currentVertex, (Consumer<Vertex<?>>) null);
        visitStack.push(currentVertex);
        visited.put(currentVertex, VertexVisitStateEnum.VisitedOnce);

        // 获取到下标对应的顶点相连的顶点集合，对每个结点深度优先搜索
        for (Vertex<?> vertex : this.graph.getRelatedVertices(v)) {
            currentRelatedVertex = this.graph.getVertices()[vertex.getIndex()];
            // 判断到环
            if (VertexVisitStateEnum.VisitedOnce.equals(visited.get(currentRelatedVertex))) {
                visitStack.push(vertex);
                // 设置环路径访问栈，返回
                withCircle = new VisitStackAndCircle(true, visitStack);
                return;
            } else if (VertexVisitStateEnum.Unvisited.equals(visited.get(currentRelatedVertex))) {
                improvedDfs(vertex.getIndex(), visitStack);
            }
        }
        // 访问完毕，出栈
        visited.put(this.graph.getVertices()[v], VertexVisitStateEnum.AllSubNodesVisited);
        visitStack.pop();
    }

    /**
     * 对结点的访问方法
     * 访问过的节点添加到DVisit数组中
     *
     * @param vertex 要访问的结点
     */
    private void visit(Vertex<?> vertex, Consumer<Vertex<?>> userVisit) {
        visitedVertices.add(vertex);
        if (userVisit != null) {
            userVisit.accept(vertex);
        }
    }

    private void visit(Vertex<?> vertex, Collection<Vertex<?>> leafCollect) {
        visitedVertices.add(vertex);
        if (leafCollect == null) {
            leafCollect = new ArrayList<>();
        }
        Boolean end = this.graph.judgeStartEnd(vertex, false);
        if (end != null && end) {
            leafCollect.add(vertex);
        }
    }
}
