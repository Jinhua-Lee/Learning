package com.mylinkedlist;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * 单链表
 * @author Jinhua
 */
public class MyLinkedList {

	/**
	 * 按照数组中ListNode结点顺序，构建一个链表
	 * @param nodes
	 * @return
	 */
	public MyNode makeList(MyNode...nodes) {
		MyNode L = nodes[0];
		for (int i = 0; i < nodes.length-1; i++) {
			nodes[i].setNext(nodes[i+1]);
		}
		return L;
	}

	/**
	 * 从尾到头打印链表
	 * @param listNode
	 * @return
	 */
	public ArrayList<Integer> printListFromTailToHead(MyNode listNode) {
		System.out.println("From tail to head:");
		ArrayList<Integer> integers = new ArrayList<>();
		if (listNode == null) {
			return integers;
		} else {
			int length = 0;
			MyNode L= listNode;
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
	 * @param L
	 * @return
	 */
	public ArrayList<Integer> printFromHeadToTail(MyNode L) {
		ArrayList<Integer> integers = new ArrayList<>();
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

	@Test
	public void test() {
		MyNode l1 = new MyNode(1);
		MyNode[] lns = new MyNode[4];
		lns[0] = new MyNode(1);
		lns[1] = new MyNode(2);
		lns[2] = new MyNode(3);
		lns[3] = new MyNode(4);
		MyNode L = makeList(lns);
		ArrayList<Integer> integers = printFromHeadToTail(L);
		System.out.println("打印构建的链表：");
		for (Integer i : integers) {
			System.out.println(i);
		}
	}
}
