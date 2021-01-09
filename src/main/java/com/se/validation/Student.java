package com.se.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

/**
 * javax.validation的校验
 * Todo：源码总结
 * 必须加入包
 *
 * @author Jinhua
 */
@Valid
@Data
@AllArgsConstructor
public class Student {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    @Min(value = 6, message = "学生年龄必须大于6岁")
    @Max(value = 25, message = "学生年龄必须小于25岁")
    private Integer age;

    /**
     * 成绩
     */
    private Double score;

    /**
     * 邮箱
     */
    private String email;

    public static void main(String[] args) {
        Student stu = new Student("小明", -1, 97.3d, "123@qq.com");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Student>> validate = validator.validate(stu);
        validate.forEach(System.out::println);
    }
}
