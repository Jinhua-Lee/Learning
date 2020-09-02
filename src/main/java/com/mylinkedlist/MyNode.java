package com.mylinkedlist;

/**
 * @author Jinhua
 */
public class MyNode {
	int value;
	MyNode next = null;

	MyNode(int value) {
		this.value = value;
	}

	public void setNext(MyNode next) {
		this.next = next;
	}
}
