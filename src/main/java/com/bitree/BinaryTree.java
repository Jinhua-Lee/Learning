package com.bitree;

import java.util.Stack;

/**
 * 二叉树定义实体类
 *
 * @author Jinhua
 */
public class BinaryTree {

    /**
     * 根节点
     */
    private Node root = null;

    public BinaryTree(Node node) {
        root = node;
    }

    public int getHeight() {
        return getHeight(this.root);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    /**
     * 判断一个节点是不是叶子节点
     *
     * @param node 待判断节点
     * @return 判断结果
     */
    public boolean isLeaf(Node node) {
        return (node.left == null && node.right == null);
    }

    /**
     * 根据值查找节点，返回匹配该值的第一个节点
     *
     * @param value 值
     * @return 返回节点，若未找到则返回空
     */
    public Node findKey(int value) {
        Node cur = root;
        while (true) {
            if (value == cur.value) {
                return cur;
            } else if (value < cur.value) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
            if (cur == null) {
                return null;
            }
        }
    }

    /**
     * 遍历的方法
     *
     * @param node 遍历的节点
     */
    private void visit(Node node) {
        System.out.println("node#getValue() = " + node.getValue());
    }

    /**
     * 先序遍历
     */
    public void preOrderTraverse() {
        System.out.println("先序遍历：");
        preOrderTraverse(root);
        System.out.println();
    }

    /**
     * 先序遍历递归操作
     *
     * @param node 待遍历的初始节点
     */
    private void preOrderTraverse(Node node) {
        if (node == null) {
            return;
        }
        visit(node);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    /**
     * 先序遍历非递归操作
     *
     * @param node 待遍历的树根
     */
    private void preOrderNonRecur(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> nodeStack = new Stack<>();
        Node curNode = node;
        visit(curNode);
        nodeStack.push(curNode);

        curNode = curNode.left;

        while ( !nodeStack.isEmpty() || curNode != null) {

            while (curNode != null) {
                visit(curNode);
                nodeStack.push(curNode);
                curNode = curNode.left;
            }
            curNode = nodeStack.pop().right;
        }
    }

    /**
     * 中序遍历
     */
    public void inOrderTraverse() {
        System.out.println("中序遍历：");
        inOrderTraverse(root);
        System.out.println();
    }

    /**
     * 中序遍历递归操作
     *
     * @param node 待遍历的初始节点
     */
    private void inOrderTraverse(Node node) {
        if (node == null) {
            return;
        }
        inOrderTraverse(node.left);
        visit(node);
        inOrderTraverse(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrderTraverse() {
        System.out.println("后序遍历：");
        inOrderTraverse(root);
        System.out.println();
    }

    /**
     * 后序遍历递归操作
     *
     * @param node 待遍历初始节点
     */
    private void postOrderTraverse(Node node) {
        if (node == null) {
            return;
        }
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        visit(node);
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);
        Node h = new Node(8);
        Node i = new Node(9);

        a.left = b;
        a.right = e;

        b.right = c;
        c.right = d;

        e.left = f;
        f.right = g;

        g.left = h;
        g.right = i;

        BinaryTree bt = new BinaryTree(a);
        bt.preOrderNonRecur(a);

    }
}
