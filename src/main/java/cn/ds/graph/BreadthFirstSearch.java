package cn.ds.graph;

import lombok.Getter;

import java.util.*;
import java.util.function.Function;

/**
 * 广度优先遍历
 *
 * @author Jinhua
 * @version 1.0
 * @date 2020/8/17 15:17
 */
@Getter
public class BreadthFirstSearch {

    /**
     * 标记是否被访问的映射
     */
    private final Map<Vertex<?>, Boolean> visited;

    /**
     * 开始访问的结点下标
     */
    private final int start;

    /**
     * 传入的图
     */
    private final MyGraph graph;

    /**
     * 被访问过的顶点数组
     */
    private final List<Vertex<?>> visitedVertices = new ArrayList<>();

    public BreadthFirstSearch(MyGraph g, int start) {
        this.start = start;
        this.graph = g;
        // 标记状态置为未访问
        visited = new HashMap<>();
        for (Vertex<?> vertex : g.getVertices()) {
            visited.put(vertex, false);
        }
    }

    public void executeBfs(Function<Vertex<?>, ?> userVisit) {
        bfs(graph, start, userVisit);
    }

    private void bfs(MyGraph g, int start, Function<Vertex<?>, ?> userVisit) {

        Queue<Vertex<?>> vertexQueue = new ArrayDeque<>();
        // 标记起点已经被访问
        visited.put(g.getVertices()[start], true);
        visit(g.getVertices()[start], userVisit);
        // 将结点加入队列
        vertexQueue.add(g.getVertices()[start]);
        while (!vertexQueue.isEmpty()) {
            Vertex<?> last = vertexQueue.poll();
            // 对图中未访问的元素
            for (Vertex<?> v : g.getRelatedVertices(last.getIndex())) {
                if (!visited.get(v)) {
                    visited.put(v, true);
                    visit(v, userVisit);
                    vertexQueue.offer(v);
                }
            }
        }
    }

    private void visit(Vertex<?> v, Function<Vertex<?>, ?> userVisit) {
        visitedVertices.add(v);
        if (userVisit != null) {
            userVisit.apply(v);
        }
    }
}
