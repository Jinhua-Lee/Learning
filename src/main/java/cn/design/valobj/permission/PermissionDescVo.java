package cn.design.valobj.permission;

import cn.design.entity.resource.Resource;
import cn.design.valobj.operation.SingleEntityOperationEnum;
import lombok.EqualsAndHashCode;

/**
 * 【权限描述】<p>
 * 解释为【对某个{@link PermissionDescVo#resource 资源}
 * {@link PermissionDescVo#allow 是否} 能
 * 进行{@link PermissionDescVo#operation 操作}】<p>
 * <p>
 * 通过【资源 + 操作】来唯一确定对象，最终的判断值为boolean值【是否能】
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/17 14:55
 */
@EqualsAndHashCode(of = {
        "resource", "operation"
})
public class PermissionDescVo {

    private Resource resource;
    private SingleEntityOperationEnum operation;

    private Boolean allow;
}
