/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/se/stream/Father.java
 * LastModified:    2021/1/19 下午10:26
 */

package com.se.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

/**
 * 父亲
 *
 * @author Jinhua
 * @date 2021/1/19 22:26
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {
        "children"
})
public class Father {

    /**
     * 名字
     */
    private String name;

    /**
     * 儿子
     */
    private List<Child> children;

    public Father(String name, List<Child> children) {
        this.name = name;
        this.children = children;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
