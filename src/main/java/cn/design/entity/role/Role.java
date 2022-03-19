package cn.design.entity.role;

import cn.design.valobj.PermissionDescVo;

import java.util.Set;

/**
 * 【角色】
 * 权限的合集
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/16 11:26
 */
public class Role {

    private Integer id;
    private Set<PermissionDescVo> permissionDescSet;
}
