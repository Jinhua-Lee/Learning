package cn.design.entity.permission;

import cn.design.entity.EntitySupport;
import lombok.Getter;

/**
 * 需要持久化到数据库的权限模板
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/26 9:21
 */
@Getter
public class PermissionTemplateEntity extends BasePermissionTemplate implements EntitySupport {
    private Integer id;
}
