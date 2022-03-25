package cn.design.model.treesupport;

import java.util.List;

/**
 * 树形结点，提供树形结点的支持
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/22 15:31
 */
public abstract class TreeNode<T> {

    /**
     * 父节点
     */
    protected TreeNode<T> parent;

    /**
     * 子节点列表
     */
    protected List<TreeNode<T>> children;
}
