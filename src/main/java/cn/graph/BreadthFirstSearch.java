/*
 * Copyright (c)    19-1-16 上午1:37.
 * Author:    Jinhua
 * PathName:    D:/IDEA/Learning/src/com/graph/BreadthFirstSearch.java
 * LastModified:    19-1-16 上午1:37
 */

package cn.graph;

import java.util.*;

/**
 * 广度优先遍历
 * @author Jinhua
 */
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

	public void executeBfs() {
		bfs(graph, start);
	}

	/**
	 * 广度优先搜索
	 * @param g 传入的图
	 * @param start 开始遍历的结点
	 */
	private void bfs(MyGraph g, int start) {

		Queue<Vertex<?>> vertexQueue = new ArrayDeque<>();
		// 标记起点已经被访问
		visited.put(g.getVertices()[start], true);
		visit(g.getVertices()[start]);
		// 将结点加入队列
		vertexQueue.add(g.getVertices()[start]);
		while(!vertexQueue.isEmpty()) {
			Vertex<?> last = vertexQueue.poll();
			// 对图中未访问的元素
			for (Vertex<?> v : g.getRelatedVertices(last.getIndex())) {
				if (!visited.get(v)) {
					visited.put(v, true);
					visit(v);
					vertexQueue.offer(v);
				}
			}
		}
	}

	/**
	 * 对结点的访问方法
	 * @param v 要访问的结点
	 */
	private void visit(Vertex<?> v) {
		visitedVertices.add(v);
		System.out.println(v.getT().toString());
	}

	public int getStart() {
		return start;
	}

	public Map<Vertex<?>, Boolean> getVisited() {
		return visited;
	}

	public MyGraph getGraph() {
		return graph;
	}

	public List<Vertex<?>> getVisitedVertices() {
		return visitedVertices;
	}
}
