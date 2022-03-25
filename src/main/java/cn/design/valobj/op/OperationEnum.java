package cn.design.valobj.op;

import cn.design.entity.resource.type.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 对资源的操作枚举
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/17 14:58
 */
@Getter
@AllArgsConstructor
public enum OperationEnum implements Operation {

    /**
     * 新增
     */
    CREATE(1, "新增", null),

    /**
     * 检索
     */
    RETRIEVE(2, "检索", null),

    /**
     * 删除
     */
    DELETE(3, "删除", null),

    /**
     * 更新：自有属性值
     */
    UPDATE_VALUE(4, "更新值", null),

    /**
     * 更新：新增关系到已有实体
     */
    UPDATE_LINK_TO_ENTITY(5, "更新_新增关系到已有实体", null),

    /**
     * 更新：取消到已有实体的关系
     */
    UPDATE_UNLINK_TO_ENTITY(6, "更新_取消到已有实体的关系", null);

    private final int type;
    private final String desc;
    private final ResourceType cascadeResourceType;

    public static Optional<OperationEnum> of(int value) {
        return Arrays.stream(OperationEnum.values())
                .filter(permission -> permission.type == value).findAny();
    }
}
