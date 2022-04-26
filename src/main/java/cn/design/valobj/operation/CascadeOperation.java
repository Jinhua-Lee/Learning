package cn.design.valobj.operation;

import cn.design.entity.resource.Resource;
import cn.design.entity.resource.type.ResourceType;
import lombok.Data;

/**
 * 【级联操作】<p>&emsp;
 * 对某{@link CascadeOperation#resourceRangeRoot 实例结点}下
 * 的{@link CascadeOperation#cascadeResourceType 资源类型}
 * 进行级联的{@link CascadeOperation#operation 操作}
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/25 22:54
 */
@Data
public class CascadeOperation implements CascadeResourceTypeCapable, Operation {

    private Resource resourceRangeRoot;
    private ResourceType cascadeResourceType;
    private SingleEntityOperationEnum operation;
}

interface CascadeResourceTypeCapable {

    /**
     * 获取【级联操作的资源类型】
     *
     * @return 【级联操作资源类型】
     */
    ResourceType getCascadeResourceType();
}
