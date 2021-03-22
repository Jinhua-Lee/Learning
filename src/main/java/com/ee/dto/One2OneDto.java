package com.ee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * 一对一关系引用对象
 * 唯一键【引用父类】： {@link OneDto#getOne()}
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/1/29 16:31
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class One2OneDto<T, R> extends OneDto<T> {

    private R r;

    public One2OneDto(T one, R r) {
        super(one);
        this.r = r;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
