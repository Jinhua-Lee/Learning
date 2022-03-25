package cn.design.entity.permission;

import cn.design.entity.resource.type.ResourceType;
import cn.design.valobj.op.Operation;

/**
 * 【权限模板】<p>&emsp;
 * 1. 起初定义角色是一系列权限的集合。<p>&emsp;
 * 2. 但和兆基讨论了一下，用户被授予了某个角色，并不意味着用户就只能访问角色规定好的某个资源。<p><p>
 * 所以增加了此概念<p><p>
 * 解释为【对某个资源类型{@link PermissionTemplate#getResourceType()}
 * 进行操作{@link PermissionTemplate#getOperation()}】<p>
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/25 21:18
 */
public interface PermissionTemplate {

    /**
     * 获取【资源类型】
     *
     * @return 【资源类型】
     */
    ResourceType getResourceType();

    /**
     * 获取【权限的操作】
     *
     * @return 【权限的操作】
     */
    Operation getOperation();
}
