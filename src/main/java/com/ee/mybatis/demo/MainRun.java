package com.ee.mybatis.demo;

import com.ee.mybatis.domain.Customer;
import com.ee.mybatis.mapper.CustomerMapper;
import com.ee.mybatis.utils.MyBatisSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Collections;

/**
 * 测试增删改查
 *
 * @author Jinhua
 * @date 2020/12/26 1:39
 */
public class MainRun {

    public static void main(String[] args) {
        SqlSession sqlSession = MyBatisSessionUtil.getInstance().getSession(true);

        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);

        Customer customer = new Customer("李金华", "男", 25);
        int insert = customerMapper.addCustomers(Collections.singletonList(customer));
        sqlSession.commit();
        sqlSession.close();
        System.out.println("insert = " + insert);
    }

}
