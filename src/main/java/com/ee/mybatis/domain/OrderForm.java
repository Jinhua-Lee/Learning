/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/mybatis/domain/OrderForm.java
 * LastModified:    2020/12/26 上午12:04
 */

package com.ee.mybatis.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单表
 *
 * @author Jinhua
 * @date 2020/12/26 0:04
 */
@Data
public class OrderForm {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 订单实体
     */
    private String orderName;

    /**
     * 订单创建实时间
     */
    private LocalDateTime createTime;

    /**
     * 订单所属客户
     */
    private Customer customer;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 订单所含商品列表
     */
    private List<Commodity> commodities;

}
