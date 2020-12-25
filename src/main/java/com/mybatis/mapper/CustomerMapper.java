/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/mybatis/mapper/CustomerMapper.java
 * LastModified:    2020/12/26 上午1:09
 */

package com.mybatis.mapper;

import com.mybatis.domain.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户数据访问层
 *
 * @author Jinhua
 * @date 2020/12/26 1:09
 */
public interface CustomerMapper {

    /**
     * 添加客户
     *
     * @param customers 客户列表
     * @return 受影响的行数
     */
    int addCustomers(@Param("customers") List<Customer> customers);

    /**
     * 更新客户
     *
     * @param customers 待更新的客户列表
     * @return 受影响的行数
     */
    int updateCustomer(@Param("customers") List<Customer> customers);

    /**
     * 删除客户
     *
     * @param ids 待删除客户列表
     * @return 受影响的行数
     */
    int deleteCustomer(@Param("ids") List<Integer> ids);

}
