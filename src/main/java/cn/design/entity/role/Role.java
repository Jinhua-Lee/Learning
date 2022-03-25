package cn.design.entity.role;

import cn.design.entity.permission.CustomizedPermissionTemplate;

import java.util.List;

/**
 * 【角色】<p>
 * 有两种实现，<p>&emsp;
 * 1. 默认角色：权限服务的默认实现，作用于租户结点，<p>&emsp;
 * 2. 自定义角色：如果有角色权限，可以自定义角色<p>
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/25 20:28
 */
public interface Role {

    /**
     * 提供获取【权限模板】的能力
     * @return 【权限模板】列表
     */
    List<CustomizedPermissionTemplate> getPermissionTemplates();
}
