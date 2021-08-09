package com.ee.ioc;

import lombok.Data;

/**
 * 行李箱<p>&emsp;
 * 解耦的Ioc实现
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/9 15:38
 */
@Data
public class SuitCaseIoc {
    private final BottomIoc bottomIoc;

    public SuitCaseIoc(BottomIoc bottomIoc) {
        this.bottomIoc = bottomIoc;
    }
}

/**
 * 行李箱底盘
 */
@Data
class BottomIoc {
    private final WheelIoc wheelIoc;

    public BottomIoc(WheelIoc wheelIoc) {
        this.wheelIoc = wheelIoc;
    }
}

/**
 * 行李箱轮子
 */
@Data
class WheelIoc {
    private final int size;

    public WheelIoc(int size) {
        this.size = size;
    }
}
