package cn.ds.graph;

import java.util.*;

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
    private Integer start;

    /**
     * 已经访问过的节点数组
     */
    private final List<Vertex<?>> visitedVertices;

    /**
     * 判断是否有环的结果存储类
     */
    private VisitStackAndCircle withCircle;

    /**
     * 构造方法，通过传入图的顶点数构建标记数组
     *
     * @param g 传入的图
     */
    public DepthFirstSearch(MyGraph g) {
        this.graph = g;
        visited = new HashMap<>();
        withCircle = new VisitStackAndCircle(false, null);
        start = 0;
        visitedVertices = new ArrayList<>();
        for (Vertex<?> vertex : g.getVertices()) {
            visited.put(vertex, VertexVisitStateEnum.Unvisited);
        }
    }

    /**
     * 提供遍历开始节点的构造方法
     *
     * @param g     构造的图
     * @param start 开始节点
     */
    public DepthFirstSearch(MyGraph g, Integer start) {
        this(g);
        this.start = start;
    }

    /**
     * 提供给对外执行的DFS遍历方法
     * 读取标记所有节点是否被访问的映射，对未访问的节点执行深度优先遍历，访问过程中也会更新映射中对应节点的状态
     */
    public void executeDfs() {
        dfs(graph, start);
        for (Vertex<?> vertex : visited.keySet()) {
            if (VertexVisitStateEnum.Unvisited.equals(visited.get(vertex))) {
                dfs(graph, vertex.getIndex());
            }
        }
    }

    /**
     * 执行DFS深度优先遍历算法
     *
     * @param g 传入的图
     * @param v 要遍历的开始顶点下标
     */
    private void dfs(MyGraph g, int v) {
        /*
         * 访问顶点并且标记访问
         */
        visit(g.getVertices()[v]);
        visited.put(g.getVertices()[v], VertexVisitStateEnum.VisitedOnce);

        // 对每个结点深度优先搜索
        // 获取到下标对应的顶点相连的顶点集合
        for (Vertex<?> vertex : g.getRelatedVertices(v)) {
            if (VertexVisitStateEnum.Unvisited.equals(visited.get(g.getVertices()[vertex.getIndex()]))) {
                dfs(g, vertex.getIndex());
            }
        }
        visited.put(g.getVertices()[v], VertexVisitStateEnum.AllSubNodesVisited);
    }

    /**
     * 提供给外部判断是否有环的方法
     * 此处提供有向图判断，无向图可扩展
     *
     * @return 是否有环
     */
    public VisitStackAndCircle judgeWithCircle() {
        Stack<Vertex<?>> visitStack = new Stack<>();
        improvedDfs(graph, start, visitStack);
        for (Vertex<?> vertex : visited.keySet()) {
            if (VertexVisitStateEnum.Unvisited.equals(visited.get(vertex))) {
                improvedDfs(graph, vertex.getIndex(), visitStack);
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
     * @param g 有向图
     * @param v 顶点
     */
    private void improvedDfs(MyGraph g, int v, Stack<Vertex<?>> visitStack) {
        Vertex<?> currentVertex = g.getVertices()[v];

        // 用于存：当前节点的所有流出节点的迭代
        Vertex<?> currentRelatedVertex;
        // 执行访问，则执行压栈，更新访问状态
        visit(currentVertex);
        visitStack.push(currentVertex);
        visited.put(currentVertex, VertexVisitStateEnum.VisitedOnce);

        // 获取到下标对应的顶点相连的顶点集合，对每个结点深度优先搜索
        for (Vertex<?> vertex : g.getRelatedVertices(v)) {
            currentRelatedVertex = g.getVertices()[vertex.getIndex()];
            if (VertexVisitStateEnum.VisitedOnce.equals(visited.get(currentRelatedVertex))) {
                visitStack.push(vertex);
                withCircle = new VisitStackAndCircle(true, visitStack);
                return;
            } else if (VertexVisitStateEnum.Unvisited.equals(visited.get(currentRelatedVertex))) {
                improvedDfs(g, vertex.getIndex(), visitStack);
            }
        }
        // 访问完毕，出栈
        visited.put(g.getVertices()[v], VertexVisitStateEnum.AllSubNodesVisited);
        visitStack.pop();
    }

    /**
     * 对结点的访问方法
     * 访问过的节点添加到DVisit数组中
     *
     * @param v 要访问的结点
     */
    private void visit(Vertex<?> v) {
        visitedVertices.add(v);
        System.out.println(v.getT().toString());
    }

    public List<Vertex<?>> getVisitedVertices() {
        return visitedVertices;
    }

    public Map<Vertex<?>, VertexVisitStateEnum> getVisited() {
        return visited;
    }

    public MyGraph getGraph() {
        return graph;
    }

    public Integer getStart() {
        return start;
    }

    public VisitStackAndCircle getWithCircle() {
        return withCircle;
    }
}
