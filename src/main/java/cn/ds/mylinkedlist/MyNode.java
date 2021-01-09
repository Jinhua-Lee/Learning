/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/ds/mylinkedlist/MyNode.java
 * LastModified:    2020/11/7 下午2:08
 */

package cn.ds.mylinkedlist;

/**
 * 节点信息
 *
 * @author Jinhua
 */
public class MyNode {

    /**
     * 节点值
     */
    int value;

    /**
     * 指向的下一个节点
     */
    MyNode next = null;

    MyNode(int value) {
        this.value = value;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }
}
