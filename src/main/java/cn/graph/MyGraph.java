package cn.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用邻接矩阵来描述图
 *
 * @author Jinhua
 * @version 1.2 加入根据入度和出度判断是否为起始节点、末端节点的方法
 * @date 2020/8/17 9:30
 */
public class MyGraph {

    /**
     * 顶点数组
     */
    private final Vertex<?>[] vertices;

    /**
     * 记录当前顶点数目
     */
    private Integer currentVertexNum;

    /**
     * 定义邻接矩阵表示顶点之间的连接关系
     * 因为用的包装类，可以直接用空表示顶点间无边
     */
    private final Double[][] adjMatrix;

    /**
     * 边的数目
     */
    private Integer edgeNum;

    /**
     * 图的类型：
     * 1：有向图.
     * 2：无向图.
     */
    private final GraphTypeEnum graphType;

    /**
     * 传入最大顶点数目和图类型
     * 1.构造顶点数组
     * 2.构造顶点关系的二维矩阵
     * 3.初始化顶点数目、边的数目、图的类型
     *
     * @param vexNum 最大顶点数
     */
    public MyGraph(Integer vexNum, GraphTypeEnum graphType) {
        vertices = new Vertex[vexNum];
        adjMatrix = new Double[vexNum][vexNum];
        edgeNum = 0;
        currentVertexNum = 0;
        this.graphType = graphType;
    }

    /**
     * 向图中加入顶点
     *
     * @param vs 要加入的顶点列表
     */
    public void addVertex(Vertex<?>... vs) {
        for (int i = currentVertexNum; i < vs.length; i++) {

            // 存入顶点数组
            vertices[currentVertexNum++] = vs[i];

            // 设置顶点在所存储的一维数组中的下标
            vs[i].setIndex(i);
        }
    }

    /**
     * 向图中添加边
     *
     * @param v1     顶点1
     * @param v2     顶点2
     * @param length 边的长度
     */
    public void addEdge(Vertex<?> v1, Vertex<?> v2, Double length) {
        int index1 = 0;
        int index2 = 0;

        // 找出两个顶点的下标
        for (int i = 0; i < vertices.length; i++) {
            if (v1.equals(vertices[i])) {
                index1 = i;
                break;
            }
        }
        for (int i = 0; i < vertices.length; i++) {
            if (v2.equals(vertices[i])) {
                index2 = i;
                break;
            }
        }

        // 存入两个顶点的距离
        adjMatrix[index1][index2] = length;

        // 若为无向图，顶点关系是相互的
        if (GraphTypeEnum.Undirected.equals(graphType)) {
            adjMatrix[index2][index1] = length;
        }

        // 边的数量增加1
        edgeNum++;
    }

    /**
     * 打印邻接矩阵
     */
    public void printAdjMatrix() {
        for (int i = 0; i < getAdjMatrix().length; i++) {
            for (int j = 0; j < getAdjMatrix()[i].length; j++) {
                System.out.print(getAdjMatrix()[i][j] + "    ");
            }
            System.out.println();
        }
    }

    /**
     * 获取与该顶点有边的所有顶点的集合（从流入找流出）
     *
     * @param v 图的一个顶点
     * @return 返回相关联的顶点集合
     */
    public List<Vertex<?>> getRelatedVertices(int v) {
        List<Vertex<?>> relatedVertices = new ArrayList<>();
        // 即是获取邻接表同一行中，不为空（表示有关联）且不在对角线（不是与自己相关）的纵坐标
        // 每次遍历到一行
        for (int i = 0; i < adjMatrix[v].length; i++) {
            Double d = adjMatrix[v][i];
            // 不为空且不在对角线上
            if (d != null && i != v) {
                relatedVertices.add(vertices[i]);
            }
        }
        return relatedVertices;
    }

    /**
     * 求一个节点在该图中的位置类型
     * 外部需判断是否是有向图，若为无向图，所有的节点都设置为中间节点类型
     *
     * @return 节点与其位置类型的映射
     */
    public Map<Vertex<?>, VertexPositionEnum> getVertexPosition() {
        Map<Vertex<?>, VertexPositionEnum> vertexPositionMap = new HashMap<>(vertices.length);
        for (Vertex<?> vertex : vertices) {
            if (graphType == GraphTypeEnum.Directed) {
                int inDegree = getDegree(vertex, DegreeTypeEnum.IN_DEGREE);
                int outDegree = getDegree(vertex, DegreeTypeEnum.OUT_DEGREE);
                if (inDegree == 0 && outDegree > 0) {
                    vertexPositionMap.put(vertex, VertexPositionEnum.StartNode);
                } else if (inDegree > 0 && outDegree == 0) {
                    vertexPositionMap.put(vertex, VertexPositionEnum.EndNode);
                } else {
                    vertexPositionMap.put(vertex, VertexPositionEnum.MiddleNode);
                }
            } else {
                vertexPositionMap.put(vertex, VertexPositionEnum.MiddleNode);
            }
        }
        return vertexPositionMap;
    }

    /**
     * 获取图的度
     *
     * @param vertex     顶点
     * @param degreeType 度的类型:
     *                   0：度；1：入度；2：出度；
     *                   其中：度 = 入度 + 出度。
     * @return 返回度
     */
    private int getDegree(Vertex<?> vertex, DegreeTypeEnum degreeType) {
        int degree = 0;
        int index = vertex.getIndex();
        switch (degreeType) {
            case IN_DEGREE: {
                for (int i = 0; i < vertices.length; i++) {
                    if (i != index && adjMatrix[i][index] != null) {
                        degree++;
                    }
                }
                break;
            }
            case OUT_DEGREE: {
                for (int i = 0; i < vertices.length; i++) {
                    if (i != index && adjMatrix[index][i] != null) {
                        degree++;
                    }
                }
                break;
            }
            case DEGREE: {
                for (int i = 0; i < vertices.length; i++) {
                    if (i != index) {
                        if (adjMatrix[i][index] != null) {
                            degree++;
                        }
                        if (adjMatrix[index][i] != null) {
                            degree++;
                        }
                    }
                }
                break;
            }
            default: {
                break;
            }
        }
        return degree;
    }

    public Double[][] getAdjMatrix() {
        return adjMatrix;
    }

    public Vertex<?>[] getVertices() {
        return vertices;
    }

    public Integer getCurrentVertexNum() {
        return currentVertexNum;
    }

    public Integer getEdgeNum() {
        return edgeNum;
    }

    public GraphTypeEnum getGraphType() {
        return graphType;
    }
}
