package cn.design.entity.permission;

import cn.design.entity.resource.type.ResourceType;
import cn.design.valobj.operation.Operation;
import lombok.Getter;

/**
 * 【基础权限模板】，包括：<p>&emsp;
 * 1. 需要持久化的权限模板实体{@link PermissionTemplateEntity}<p>&emsp;
 * 2. 不需要持久化的值对象{@link cn.design.valobj.permission.template.PermissionTemplateVo}<p><p>
 * 在进行具体鉴权操作前，两种类型都需要加入【权限模板列表】，以当前类型{@link BasePermissionTemplate}进行判断。<p>
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/22 13:50
 */
@Getter
public abstract class BasePermissionTemplate implements PermissionTemplate {

    protected ResourceType resourceType;
    protected Operation operation;
}
