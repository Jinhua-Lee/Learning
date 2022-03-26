package cn.design.entity.permission;

import cn.design.entity.resource.type.ResourceType;
import cn.design.valobj.op.Operation;

/**
 * 【权限模板】<p>&emsp;
 * 1. 起初定义角色是一系列权限的集合。<p>&emsp;
 * 2. 但和兆基讨论了一下，用户被授予了某个角色，并不意味着用户就只能访问角色规定好的某个资源。<p><p>
 * 所以增加了此概念<p><p>
 * 解释为：<p>&emsp;
 * 1. 对【某个资源类型{@link PermissionTemplate#getSourceType()}】
 * 进行操作{@link PermissionTemplate#getOperation()}】<p>&emsp;
 * 2. 将【某个资源类型{@link PermissionTemplate#getSourceType()}】
 * 【关联/取消关联{@link PermissionTemplate#getOperation()}】到【另一个类型{@link PermissionTemplate#getTargetType()}}】<p>&emsp;
 * 3. 授予
 * 【某个资源{@link PermissionTemplate#getSourceType()}】
 * 【级联{@link PermissionTemplate#getCascade()}}】
 * 【操作{@link PermissionTemplate#getOperation()}}】
 * 【另一个类型资源{@link PermissionTemplate#getTargetType()}}】的能力
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
     * @see PermissionTemplate#getTargetType() 目标资源类型
     */
    ResourceType getSourceType();

    /**
     * 获取【权限的操作】
     *
     * @return 【权限的操作】
     */
    Operation getOperation();

    /**
     * 获取【目标资源的类型】<p>&emsp;
     * <p>
     * 比如，【用户A】以【角色A】的身份进行操作，将【用户B】关联到已有的【角色B】<p>&emsp;
     * 1. 【用户B】就作为资源类型{@link PermissionTemplate#getSourceType()}；<p>&emsp;
     * 2. 【角色B】就作为【目标类型】
     *
     * @return 【目标资源类型】
     */
    ResourceType getTargetType();

    /**
     * 是否级联
     *
     * @return 是否级联操作
     */
    Boolean getCascade();
}
