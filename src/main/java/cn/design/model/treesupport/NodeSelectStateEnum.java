package cn.design.model.treesupport;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 结点选中状态，根据原有概念，细化到数据结构中
 *
 * @author Jinhua
 * @version 1.0
 * @date 25/04/2022 09:06
 */
@Getter
@AllArgsConstructor
public enum NodeSelectStateEnum {

    /**
     * 叶子，允许当前, 子结点全允许（原概念为【全选】）
     */
    LEAF_ALLOW_ALL_CHILDREN(0, "叶子，允许当前, 子结点全允许"),

    /**
     * 非叶子，允许当前（原概念为【半选】）
     */
    NON_LEAF_ALLOW_CURRENT(1, "非叶子，允许当前"),

    /**
     * 叶子，允许当前，禁止所有子结点（原概念，全不选）
     */
    LEAF_ALLOW_CURRENT_DENY_ALL_CHILDREN(2, "叶子，允许当前，禁止所有子结点"),

    /**
     * 叶子，禁止当前，子结点全部禁止
     */
    LEAF_DENY(3, "叶子，禁止当前，子结点全部禁止");

    private final int type;
    private final String desc;

    public static Optional<NodeSelectStateEnum> of(int value) {
        return Arrays.stream(NodeSelectStateEnum.values())
                .filter(nState -> nState.type == value).findAny();
    }
}
