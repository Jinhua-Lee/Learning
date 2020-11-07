package com.mylinkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 单链表
 *
 * @author Jinhua
 */
public class MyLinkedList {

    /**
     * 按照数组中ListNode结点顺序，构建一个链表
     *
     * @param nodes 节点
     * @return 返回链表头节点
     */
    public MyNode makeList(MyNode... nodes) {
        MyNode L = nodes[0];
        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].setNext(nodes[i + 1]);
        }
        return L;
    }

    /**
     * 从尾到头打印链表
     *
     * @param listNode 单链表
     * @return 链表的值
     */
    public List<Integer> printListFromTailToHead(MyNode listNode) {
        System.out.println("From tail to head:");
        List<Integer> integers = new ArrayList<>();
        if (listNode == null) {
            return integers;
        } else {
            int length = 0;
            MyNode L = listNode;
            while (L.next != null) {
                length++;
                L = L.next;
            }
            MyNode p;
            for (int i = length; i >= 0; i--) {
                p = listNode;
                for (int j = 0; j < i; j++) {
                    p = p.next;
                }
                integers.add(p.value);
                System.out.println(p.value);
            }
        }
        return integers;
    }

    /**
     * 从头到尾遍历
     *
     * @param L 单链表
     * @return 遍历结果的列表
     */
    public List<Integer> printFromHeadToTail(MyNode L) {
        List<Integer> integers = new ArrayList<>();
        System.out.println("From Head To Tail");
        MyNode p = L;
        MyNode q = null;
        while (p != null) {
            System.out.println(p.value);
            integers.add(p.value);
            p = p.next;
        }
        return integers;
    }

    public static void main(String[] args) {
        MyNode l1 = new MyNode(1);
        MyNode[] lns = new MyNode[4];
        lns[0] = new MyNode(1);
        lns[1] = new MyNode(2);
        lns[2] = new MyNode(3);
        lns[3] = new MyNode(4);

        MyLinkedList linkedList = new MyLinkedList();

        MyNode myNode = linkedList.makeList(lns);
        List<Integer> integers = linkedList.printFromHeadToTail(myNode);
        System.out.println("打印构建的链表：");
        for (Integer i : integers) {
            System.out.println(i);
        }
    }
}
