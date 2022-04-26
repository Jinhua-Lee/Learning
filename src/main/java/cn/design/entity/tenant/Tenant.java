package cn.design.entity.tenant;

import cn.design.entity.EntitySupport;
import cn.design.entity.resource.ResourceTypeCapable;
import cn.design.entity.resource.type.AuthResourceTypeEnum;
import cn.design.entity.resource.type.ResourceType;
import lombok.Getter;

import java.util.List;

/**
 * 【租户】单结点
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/25 20:48
 */
@Getter
public class Tenant implements EntitySupport, ResourceTypeCapable {

    private Integer id;
    private Tenant parent;
    private List<Tenant> children;

    @Override
    public ResourceType getResourceType() {
        return AuthResourceTypeEnum.TENANT;
    }
}
