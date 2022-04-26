package cn.design.valobj.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 单实体操作
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/17 14:58
 */
@Getter
@AllArgsConstructor
public enum SingleEntityOperationEnum implements Operation {

    /**
     * 新增
     */
    CREATE(1, "新增"),

    /**
     * 检索
     */
    RETRIEVE(2, "检索"),

    /**
     * 删除
     */
    DELETE(3, "删除"),

    /**
     * 更新：自有属性值
     */
    UPDATE_VALUE(4, "更新值");

    private final int type;
    private final String desc;

    public static Optional<SingleEntityOperationEnum> of(int value) {
        return Arrays.stream(SingleEntityOperationEnum.values())
                .filter(permission -> permission.type == value).findAny();
    }
}
