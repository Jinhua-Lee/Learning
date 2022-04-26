package cn.design.entity.permission;

import cn.design.entity.resource.type.ResourceType;
import cn.design.valobj.operation.Operation;

/**
 * 【权限模板】<p>&emsp;
 * 1. 起初定义角色是一系列权限的集合。<p>&emsp;
 * 2. 但和兆基讨论了一下，用户被授予了某个角色，并不意味着用户就只能访问角色规定好的某个资源。<p>
 * 所以增加了此概念<p><p>
 * 解释为：<p>&emsp;
 * 有当前接口的实例对象，就代表了能对【某个{@link PermissionTemplate#getResourceType() 资源类型}】
 * 进行{@link PermissionTemplate#getOperation() 操作}】
 * <p>
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/25 21:18
 */
public interface PermissionTemplate {

    /**
     * 获取【待操作资源】类型，即是源类型
     *
     * @return 【资源类型】
     */
    ResourceType getResourceType();

    /**
     * 获取【操作】
     *
     * @return 【操作】
     */
    Operation getOperation();
}
