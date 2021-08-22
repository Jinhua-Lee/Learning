package cn.ds.graph;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * 图的顶点表示类
 * 无空参构造方法，暂定义为不可以设置name和信息实体，通过构造方法写入
 * 在一维数组中的下标可以在构造后通过setter写入
 * 唯一性：一维数组中的顶点是唯一的，所以可通过实体的唯一性唯一确定顶点的唯一性{@link Vertex#getT()}
 *
 * @author Jinhua
 * @version 1.0
 * @date 2020/8/17 9:21
 */
@Data
@EqualsAndHashCode(of = "t")
public class Vertex<T> {

    /**
     * 包含顶点信息的实体
     */
    @Setter(AccessLevel.NONE)
    private final T t;

    /**
     * 顶点在一维数组中的下标
     */
    private int index = -1;

    /**
     * 顶点的别名
     */
    @Setter(AccessLevel.NONE)
    private final String name;

    public Vertex(T t, String name) {
        this.name = name;
        this.t = t;
    }
}
