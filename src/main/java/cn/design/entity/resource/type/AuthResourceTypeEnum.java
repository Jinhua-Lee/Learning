package cn.design.entity.resource.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 权限服务自身，为完成权限功能的资源
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/22 11:40
 */
@Getter
@AllArgsConstructor
public enum AuthResourceTypeEnum implements ResourceType {

    /**
     * 用户、角色、租户结点树、项目
     */
    USER(1, "用户"),
    ROLE(2, "角色"),
    TENANT(3, "租户结点树"),
    PROJECT(4, "项目");

    private final int type;
    private final String desc;

    public static Optional<? extends ResourceType> of(int value) {
        return Arrays.stream(AuthResourceTypeEnum.values())
                .filter(authResType -> authResType.type == value).findAny();
    }
}
