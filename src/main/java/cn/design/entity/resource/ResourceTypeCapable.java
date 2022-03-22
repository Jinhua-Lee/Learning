package cn.design.entity.resource;

import cn.design.entity.resource.type.ResourceType;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/22 16:50
 */
public interface ResourceTypeCapable {

    /**
     * 对外有资源类型的提供能力
     *
     * @return 资源类型
     */
    ResourceType getResourceType();
}
