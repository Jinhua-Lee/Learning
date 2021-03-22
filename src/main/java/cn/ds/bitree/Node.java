package cn.ds.bitree;

import lombok.Data;

/**
 * 树的节点引用类
 *
 * @author Jinhua
 * @date 2020/9/2 23:21
 */
@Data
public class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
