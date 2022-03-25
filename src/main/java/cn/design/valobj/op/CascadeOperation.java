package cn.design.valobj.op;

import cn.design.entity.resource.type.ResourceType;

/**
 * 【级联向下操作】，需要指定结点类型
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/25 22:54
 */
public class CascadeOperation implements CascadeResourceTypeCapable, Operation {

    @Override
    public ResourceType getCascadeResourceType() {
        return null;
    }
}

interface CascadeResourceTypeCapable {

    /**
     * 获取【级联操作的资源类型】
     *
     * @return 【级联操作资源类型】
     */
    ResourceType getCascadeResourceType();
}
