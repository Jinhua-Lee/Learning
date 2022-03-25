package cn.design.entity.permission;

import cn.design.entity.EntitySupport;
import cn.design.entity.resource.type.ResourceType;
import cn.design.valobj.op.Operation;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 【用户自定义权限模板】<p>
 * 在进行具体鉴权操作前，需要加到【权限模板列表】中，包括默认的
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/22 13:50
 */
@Getter
@EqualsAndHashCode(of = {
        "resourceType", "operation"
})
public class CustomizedPermissionTemplate implements PermissionTemplate, EntitySupport {

    private Integer id;

    private ResourceType resourceType;
    private Operation operation;
}
