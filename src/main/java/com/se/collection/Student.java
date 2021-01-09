package com.se.collection;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

/**
 * 学生类
 * 测试TreeSet的有序性和唯一性
 *
 * @author Jinhua
 */
@Data
@EqualsAndHashCode
public class Student {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 班级号
     */
    private int classNo;


    public Student(String name, int age, String sex, int classNo) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.classNo = classNo;
    }

    public static void main(String[] args) {
        Set<Student> set = new TreeSet<>((s1, s2) -> {
            // 先名字升序
            int num1 = s1.getName().compareTo(s2.getName());
            // 其次班级号升序
            int num2 = num1 == 0 ? s1.getClassNo() - s2.getClassNo() : num1;
            // 再其次性别升序
            int num3 = num2 == 0 ? s1.getSex().compareTo(s2.getSex()) : num2;
            // 最后年纪升序
            return num3 == 0 ? s1.getAge() - s2.getAge() : num3;
        });
        set.add(new Student("李金华", 21, "男", 1503));
        set.add(new Student("李文凯", 21, "男", 1501));
        set.add(new Student("高亚文", 20, "女", 1502));

        for (Student student : set) {
            System.out.println(student.getName() + "-----" + student.getAge()
                    + "-----" + student.getSex() + "-----" + student.getClassNo());
        }

        Map<Integer, Student> is = new HashMap<>(set.size() * 2);
        int i = 0;
        for (Student student : set) {
            is.put(i++, student);
        }

        Set<Map.Entry<Integer, Student>> set3 = is.entrySet();
        for (Map.Entry<Integer, Student> entry : set3) {
            Integer key = entry.getKey();
            Student value = entry.getValue();
            System.out.println("Key:" + key + "-----Value:" + value.getName() + "-----" + value.getAge()
                    + "-----" + value.getSex() + "-----" + value.getClassNo());
        }
    }
}
