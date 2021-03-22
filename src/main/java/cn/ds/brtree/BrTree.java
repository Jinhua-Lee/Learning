package cn.ds.brtree;

import lombok.Data;

/**
 * 红黑树模拟
 *
 * @author Jinhua
 * @date 2021/1/14 22:12
 */
@Data
public class BrTree<E> {


    private static class BrTreeNode<E> {

        boolean black;
        E data;

        BrTreeNode<E> parent;
        BrTreeNode<E> left;
        BrTreeNode<E> right;

        public BrTreeNode(boolean black, E data) {
            this.black = black;
            this.data = data;
        }

    }

    /**
     * 空结点
     */
    private BrTreeNode<E> root;

    /**
     * 空结点
     */
    private final BrTreeNode<E> nil;

    public BrTree() {
        nil = new BrTreeNode<>(true, null);
        nil.left = nil.right = nil.parent = nil;
        root = nil;
    }

    /**
     * 插入元素
     *
     * @param element 待插入元素
     */
    public void insert(E element) {

    }


}
