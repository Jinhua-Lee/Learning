package cn.design.valobj.operation;

import cn.design.entity.resource.type.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 【实体关系操作】，有两种类型：<p>&emsp;
 * 1. {@link LinkTypeEnum#LINK 新增}关系到{@link EntityRelationOperation#targetType 指定类型}；<p>&emsp;
 * 2. {@link LinkTypeEnum#UNLINK 删除}关系到{@link EntityRelationOperation#targetType 指定类型}；<p>
 *
 * @author Jinhua
 * @version 1.0
 * @date 25/04/2022 09:48
 */
public class EntityRelationOperation implements Operation {

    private ResourceType targetType;
    private LinkTypeEnum linkType;

    /**
     * 关系操作：新增关系或取消关系
     */
    @Getter
    @AllArgsConstructor
    enum LinkTypeEnum {

        /**
         * 新增关系
         */
        LINK(5, "新增关系"),
        /**
         * 取消关系
         */
        UNLINK(6, "取消关系");

        private final int type;
        private final String desc;

        public static Optional<LinkTypeEnum> of(int value) {
            return Arrays.stream(LinkTypeEnum.values())
                    .filter(lType -> lType.type == value).findAny();
        }
    }
}
