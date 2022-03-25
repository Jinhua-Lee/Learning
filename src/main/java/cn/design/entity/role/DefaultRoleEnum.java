package cn.design.entity.role;

import cn.design.entity.permission.CustomizedPermissionTemplate;
import cn.design.entity.permission.holder.DefaultPermissionTemplateHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 权限服务提供的【默认角色】
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/25 20:30
 */
@Getter
@AllArgsConstructor
public enum DefaultRoleEnum implements Role {

    /**
     * 租户子树管理员：级联管理租户结点，平台管理员是特殊类型
     */
    TENANT_SUBTREE_MANAGER(1, "租户子树管理员") {
        @Override
        public List<CustomizedPermissionTemplate> getPermissionTemplates() {
            return DefaultPermissionTemplateHolder.TENANT_SUBTREE_MANAGER_TEMPLATES;
        }
    },

    /**
     * 租户管理员：管理租户单结点
     */
    TENANT_MANAGER(2, "租户管理员") {
        @Override
        public List<CustomizedPermissionTemplate> getPermissionTemplates() {
            return DefaultPermissionTemplateHolder.TENANT_MANAGER_TEMPLATES;
        }
    },

    /**
     * 项目管理员：能管理项目内的信息
     */
    PROJECT_MANAGER(3, "项目管理员") {
        @Override
        public List<CustomizedPermissionTemplate> getPermissionTemplates() {
            return DefaultPermissionTemplateHolder.PROJECT_MANAGER_TEMPLATES;
        }
    };

    private final int type;
    private final String desc;

    public static Optional<DefaultRoleEnum> of(int value) {
        return Arrays.stream(DefaultRoleEnum.values())
                .filter(dRole -> dRole.type == value).findAny();
    }

    @Override
    public abstract List<CustomizedPermissionTemplate> getPermissionTemplates();
}
