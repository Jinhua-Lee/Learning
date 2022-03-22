package cn.design.valobj.permission;

import cn.design.entity.resource.type.ResourceType;
import cn.design.valobj.op.PermissionOpEnum;
import lombok.EqualsAndHashCode;

/**
 * 【权限模板】<p>
 * 起初定义角色是一系列权限的集合。但和兆基讨论了以下，用户被授予了某个角色，并不意味着用户就只能访问角色规定好的某个资源。
 * 所以增加了此概念<p><p>
 * 解释为【对某个资源类型{@link PermissionTemplateVo#resourceType}
 * 是否能{@link PermissionTemplateVo#judgeResult}
 * 进行操作{@link PermissionTemplateVo#permissionOp}】<p>
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/22 13:50
 * @see PermissionDescVo 【权限描述】
 */
@EqualsAndHashCode(of = {
        "resourceType", "permissionOp"
})
public abstract class PermissionTemplateVo {

    private ResourceType resourceType;
    private PermissionOpEnum permissionOp;

    private Boolean judgeResult;
}
