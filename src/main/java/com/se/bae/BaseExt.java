package com.se.bae;

import lombok.*;

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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {
        "age", "info"
})
public class BaseExt extends Base {

    private Boolean age;
    private String info;
}
