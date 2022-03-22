package cn.design.entity.resource;

import cn.design.entity.resource.type.ResourceType;
import lombok.Getter;

/**
 * 资源，是权限判断的基本单位
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/16 11:26
 */
@Getter
public abstract class BaseResource implements Resource, ResourceTypeCapable {

    protected Integer id;
    protected ResourceType resourceType;
}
