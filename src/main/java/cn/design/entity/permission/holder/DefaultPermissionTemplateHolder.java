package cn.design.entity.permission.holder;

import cn.design.entity.permission.BasePermissionTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 【默认权限模板】的持有类，是一些写死的配置<p>
 * 以下类型角色的权限模板列表<p>&emsp;
 * 1. 平台管理员<p>&emsp;
 * 2. 租户管理员<p>&emsp;
 * 3. 项目管理员<p>&emsp;
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/25 21:15
 */
public class DefaultPermissionTemplateHolder {

    public static final List<BasePermissionTemplate> TENANT_SUBTREE_MANAGER_TEMPLATES;
    public static final List<BasePermissionTemplate> TENANT_MANAGER_TEMPLATES;
    public static final List<BasePermissionTemplate> PROJECT_MANAGER_TEMPLATES;

    static {
        TENANT_SUBTREE_MANAGER_TEMPLATES = new ArrayList<>();
        TENANT_MANAGER_TEMPLATES = new ArrayList<>();
        PROJECT_MANAGER_TEMPLATES = new ArrayList<>();

        initPlatformManagerTemplates();
        initTenantManagerTemplates();
        initProjectManagerTemplates();
    }

    private static void initPlatformManagerTemplates() {
        // TODO: 2022/3/25 完善平台管理员的权限模板
    }

    private static void initTenantManagerTemplates() {
        // TODO: 2022/3/25 完善租户管理员的权限模板
    }

    private static void initProjectManagerTemplates() {
        // TODO: 2022/3/25 完善项目管理员的权限模板
    }
}
