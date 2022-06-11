package com.se.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


import java.util.Set;

/**
 * javax.validation的校验
 *
 * @author Jinhua
 */
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
     * Valid注解用作嵌套校验
     */
    @Valid
    private StuExtInfo stuExtInfo;

    public static void main(String[] args) {
        Student stu = new Student("小明", -1, new StuExtInfo(104.2d, "1@qq.com"));
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Student>> validate = validator.validate(stu);
        validate.forEach(System.out::println);
    }

    @Getter
    @AllArgsConstructor
    private static class StuExtInfo {

        /**
         * 成绩
         */
        @Min(value = 0, message = "成绩必须非负")
        @Max(value = 100, message = "成绩最高是100")
        private Double score;

        /**
         * 邮箱
         */
        @Email(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
        private String email;
    }
}
