package cn.design.entity.resource.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务资源类型的枚举定义
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/22 11:36
 */
@Getter
@AllArgsConstructor
public enum BizResTypeEnum implements ResourceType {

    /**
     * 模型结点、图形、报表等，待细化
     */
    MODEL_NODE,
    DRAW,
    PEC_REPORT;

}
