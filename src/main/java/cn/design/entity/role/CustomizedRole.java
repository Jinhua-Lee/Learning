package cn.design.entity.role;

import cn.design.entity.EntitySupport;
import cn.design.entity.permission.BasePermissionTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * 【自定义角色】
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/16 11:26
 */
@Getter
@EqualsAndHashCode(of = "id")
public class CustomizedRole implements Role, EntitySupport {

    private Integer id;
    private List<BasePermissionTemplate> permissionTemplates;
}
