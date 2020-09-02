package com.validation;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@RequiredArgsConstructor()
public class Student {

    private String name;

    @Min(value = 6, message = "学生年龄必须大于6岁")
    @Max(value = 25, message = "学生年龄必须小于25岁")
    private Integer age;

    private Double score;

    private String email;
}
