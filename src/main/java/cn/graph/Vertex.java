package cn.graph;

import java.util.Objects;

/**
 *  图的顶点表示类
 *      无空参构造方法，暂定义为不可以设置name和信息实体，通过构造方法写入
 *      在一维数组中的下标可以在构造后通过setter写入
 *      唯一性：一维数组中的顶点是唯一的，所以可通过实体的唯一性唯一确定顶点的唯一性{@link Vertex#getT()}
 * @author Jinhua
 * @date 2020/8/17 9:21
 * @version 1.0
 */
public class Vertex<T> {

    /**
     * 包含顶点信息的实体
     */
    private final T t;

    /**
     * 顶点在一维数组中的下标
     */
    private Integer index;

    /**
     * 顶点的别名
     */
    private final String name;

    public Vertex(T t, String name) {
        this.name = name;
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public Integer getIndex() {
        return index;
    }

    /**
     * 设置顶点在一维数组中的下标
     * @param index 数组下标
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(getT(), vertex.getT());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getT());
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "t=" + t +
                ", index=" + index +
                ", name='" + name + '\'' +
                '}';
    }
}
