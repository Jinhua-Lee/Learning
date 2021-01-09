/*
 * Copyright (c)    19-1-16 上午12:14.
 * Author:    Jinhua
 * PathName:    D:/IDEA/Learning/src/com/graph/Item.java
 * LastModified:    19-1-14 下午10:29
 */

package cn.ds.graph;

/**
 * 信息实体类
 *
 * @author Jinhua
 */
public class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                '}';
    }
}
