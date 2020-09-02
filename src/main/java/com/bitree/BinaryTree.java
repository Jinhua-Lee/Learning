package com.bitree;

/**
 * 二叉树定义实体类
 * @author Jinhua
 */
public class BinaryTree {
	class Node {
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

	private Node root = null;

	BinaryTree(int value) {
		root = new Node(value);
		root.left = null;
		root.right = null;
	}

	public int getHeight() {
		return getHeight(this.root);
	}

	private int getHeight(Node node) {
		if (node == null) {
			return 0;
		}
		else {
			return 1 + Math.max(getHeight(node.left), getHeight(node.right));
		}
	}

	/**
	 * 判断一个节点是不是叶子节点
	 * @param node 待判断节点
	 * @return 判断结果
	 */
	public boolean isLeaf(Node node) {
		return (node.left == null && node.right == null);
	}

	/**
	 * 根据值查找节点，返回匹配该值的第一个节点
	 * @param value 值
	 * @return 返回节点，若未找到则返回空
	 */
	public Node findkey(int value) {
		Node cur = root;
		while (true) {
			if (value == cur.value) {
				return cur;
			}
			else if (value < cur.value) {
				cur = cur.left;
			}
			else if (value > cur.value) {
				cur = cur.right;
			}
			if (cur == null) {
				return null;
			}
		}
	}

	public void preOrderTraverse() {
		System.out.println("先序遍历：");
		preOrderTraverse(root);
		System.out.println();
	}

	/**
	 * 先序遍历递归操作
	 * @param node 待遍历的初始节点
	 */
	private void preOrderTraverse(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.getValue());
		preOrderTraverse(node.left);
		preOrderTraverse(node.right);
	}

	public void inOrderTraverse() {
		System.out.println("中序遍历：");
		inOrderTraverse(root);
		System.out.println();
	}

	/**
	 * 中序遍历递归操作
	 * @param node 待遍历的初始节点
	 */
	private void inOrderTraverse(Node node) {
		if (node == null) {
			return;
		}
		inOrderTraverse(node.left);
		System.out.println(node.getValue());
		inOrderTraverse(node.right);
	}


	public void postOrderTraverse() {
		System.out.println("后序遍历：");
		inOrderTraverse(root);
		System.out.println();
	}

	/**
	 * 后序遍历递归操作
	 * @param node 待遍历初始节点
	 */
	private void postOrderTraverse(Node node) {
		if (node == null) {
			return;
		}
		postOrderTraverse(node.left);
		postOrderTraverse(node.right);
		System.out.println(node.getValue());
	}
}
