/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/ee/dto/One2MultiDto.java
 * LastModified:    2021/1/29 下午9:56
 */

package com.ee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

/**
 * 一对多引用关系对象
 * 唯一键：【引用父类】一 {@link OneDto#getOne()}
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/1/29 16:13
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class One2MultiDto<T, R> extends OneDto<T> {

    /**
     * 多的一方列表
     */
    private List<R> multiList;

    public One2MultiDto(T t, List<R> multiList) {
        super(t);
        this.multiList = multiList;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
