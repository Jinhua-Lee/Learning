/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/bitree/Node.java
 * LastModified:    2020/9/2 下午11:21
 */

package com.bitree;

/**
 * 树的节点引用类
 * @author Jinhua
 * @date 2020/9/2 23:21
 */
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
