package cn.design.entity.permission;

import cn.design.entity.resource.type.ResourceType;
import cn.design.valobj.op.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 【默认权限模板】枚举
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/25 21:26
 */
@Getter
@AllArgsConstructor
public enum DefaultPermissionTemplateEnum implements PermissionTemplate {



    /**
     * // TODO: 2022/3/25 定义枚举
     */
    DEMO(1, "案例") {
        @Override
        public ResourceType getResourceType() {
            return null;
        }

        @Override
        public Operation getOperation() {
            return null;
        }
    };

    private final int type;
    private final String desc;

    public static Optional<? extends PermissionTemplate> of(int value) {
        return Arrays.stream(DefaultPermissionTemplateEnum.values())
                .filter(dpTemplate -> dpTemplate.type == value).findAny();
    }

    @Override
    public abstract ResourceType getResourceType();

    @Override
    public abstract Operation getOperation();
}
