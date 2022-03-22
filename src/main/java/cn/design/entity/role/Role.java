package cn.design.entity.role;

import cn.design.entity.EntitySupport;
import cn.design.valobj.permission.PermissionTemplateVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Set;

/**
 * 【角色】
 * 权限模板的集合
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/16 11:26
 */
@Getter
@EqualsAndHashCode(of = "id")
public class Role implements EntitySupport {

    private Integer id;
    private Set<PermissionTemplateVo> permissionTemplates;
}
