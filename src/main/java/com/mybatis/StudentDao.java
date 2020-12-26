package com.mybatis;

import com.jedis.Student;

/**
 * 学生访问类
 *
 * @author Jinhua
 * @version 1.0
 * @date 2020/12/25 17:16
 */
public interface StudentDao {

    /**
     * 添加学生类的方法
     *
     * @param stu 待添加
     * @return 添加结果
     */
    int addStudent(Student stu);
}
