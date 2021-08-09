package com.ee.ioc;

import lombok.Data;

/**
 * 行李箱：<p>&emsp;
 * 高度依赖于轮子的尺寸。
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/9 15:30
 */
@Data
public class SuitCase {
    private final Bottom bottom;

    public SuitCase(int wheelSize) {
        this.bottom = new Bottom(wheelSize);
    }
}

/**
 * 行李箱底盘
 */
@Data
class Bottom {
    private final Wheel wheel;

    public Bottom(int wheelSize) {
        this.wheel = new Wheel(wheelSize);
    }
}

/**
 * 行李箱轮子
 */
@Data
class Wheel {
    private final int wheelSize;

    public Wheel(int wheelSize) {
        this.wheelSize = wheelSize;
    }
}
