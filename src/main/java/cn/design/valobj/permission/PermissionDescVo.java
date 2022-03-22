package cn.design.valobj.permission;

import cn.design.entity.resource.BaseResource;
import cn.design.valobj.op.PermissionOpEnum;
import lombok.EqualsAndHashCode;

/**
 * 【权限描述】<p>
 * 解释为【对某个资源{@link PermissionDescVo#baseResource}
 * 是否能{@link PermissionDescVo#judgeResult}
 * 进行操作{@link PermissionDescVo#permissionOp}】<p>
 * <p>
 * 通过【资源 + 操作】来唯一确定对象，最终的判断值为boolean值【是否能】
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/17 14:55
 */
@EqualsAndHashCode(of = {
        "baseResource", "permissionOp"
})
public abstract class PermissionDescVo {

    private BaseResource baseResource;
    private PermissionOpEnum permissionOp;

    private Boolean judgeResult;
}
