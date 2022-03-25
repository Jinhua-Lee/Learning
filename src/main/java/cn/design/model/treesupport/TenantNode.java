package cn.design.model.treesupport;

import cn.design.entity.role.CustomizedRole;

/**
 * 【租户结点】实现上层对下层的控制权限
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/22 15:37
 */
public class TenantNode extends TreeNode<TenantNode> {

    public Object evaluate(CustomizedRole role) {
        return null;
    }
}
