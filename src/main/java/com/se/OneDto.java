package com.se;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * 单个属性的包装对象
 * <p>
 * 唯一键：{@link OneDto#one}
 * <p>
 * 主要用于，被继承，增加属性，但唯一键继承父类
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/1/29 18:09
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OneDto<T> {

    /**
     * 唯一属性
     */
    private T one;

    public OneDto(T one) {
        this.one = one;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
