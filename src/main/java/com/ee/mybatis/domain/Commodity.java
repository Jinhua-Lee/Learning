package com.ee.mybatis.domain;

import java.math.BigDecimal;

/**
 * 商品实体类
 *
 * @author Jinhua
 * @date 2020/12/26 0:19
 */
public class Commodity {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 商品产地
     */
    private String produceCity;

}
