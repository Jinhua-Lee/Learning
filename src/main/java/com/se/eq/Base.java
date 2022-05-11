package com.se.eq;

import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 测试Lombok唯一键比较的父类
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/5/18 14:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@Slf4j
public class Base {

    private Integer id;

    public static void main(String[] args) {
        Base base1 = Child.builder().id(1).build();
        Base base2 = Child.builder().id(1).info("ljh").build();
        // true
        log.info("父类属性相同，子类属性不同：{}", Objects.equals(base1, base2));

    }
}


/**
 * 子类，查看equals和hashCode方法的反编译。<p>
 * <p>
 * 总结：<p>&emsp;
 * 1) callSuper，调用父类比较方法，不代表不比较子类属性。<p>&emsp;
 * 2) 仍然需要显式指定子类【不需比较】的属性。<p>
 * <p>
 * 小结equals通常步骤：<p>&emsp;
 * 1) 是否当前对象；<p>&emsp;
 * 2) 是否同一类型。<p>&emsp;
 * 3) 属性比较。<p>&emsp;&emsp;
 *      a. 调用父类比较方法？<p>&emsp;&emsp;
 *      b. 子类属性比较。<p>
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/5/18 14:57
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {
        "info"
})
class Child extends Base {
    private String info;
}
