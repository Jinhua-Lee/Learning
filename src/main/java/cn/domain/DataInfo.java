/*
 * Copyright (c)    2019/10/17 下午3:25.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/cn/diagram/DataInfo.java
 * LastModified:    2019/10/17 下午3:25
 */

package cn.domain;

import lombok.Data;

/**
 * 存储信息记录的对应的属性和值
 * @author Jinhua
 */
@Data
public class DataInfo {

    // 记录对应哪个台账
    private Integer account_id;

    // 信息版本号
    private Integer version;

    // 存储记录信息的包含属性与属性值的json格式字符串
    private String ext;
}
