package cn.ds.multitree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 多叉树定义
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/19 9:48
 */
@Data
public class MultiTree<T> {

    /**
     * 根结点
     */
    private Node<T> root;

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode(of = "data")
    static class Node<T> {
        T data;
        List<Node<T>> children;

        public boolean isLeaf() {
            return CollectionUtils.isEmpty(children);
        }

        /**
         * 找出【以当前结点为根】的【所有叶子结点】
         *
         * @return 【以当前结点为根】的【所有叶子结点】列表
         */
        public List<Node<T>> leafAll() {
            List<Node<T>> leafAll = new ArrayList<>();
            traverseLeaf(leafAll, this);
            return leafAll;
        }

        /**
         * 【递归】将当前结点为根的叶子结点，全部加入到列表中
         *
         * @param result 叶子结点列表
         * @param cur    当前遍历结点
         */
        private void traverseLeaf(@NotNull List<Node<T>> result, Node<T> cur) {
            if (cur == null) {
                return;
            }
            if (cur.isLeaf()) {
                result.add(cur);
            } else {
                cur.children.forEach(child -> traverseLeaf(result, child));
            }
        }
    }
}
