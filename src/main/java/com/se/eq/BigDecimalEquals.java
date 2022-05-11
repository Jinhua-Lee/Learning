package com.se.eq;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2022/2/16 14:09
 */
@Slf4j
public class BigDecimalEquals {

    public static void main(String[] args) {
        BigDecimal bd1 = new BigDecimal(1);
        BigDecimal bd2 = new BigDecimal(1);
        // 输出true
        log.info("bd1和bd2相等：{}", bd1.equals(bd2));

        BigDecimal bd3 = new BigDecimal(1);
        // FIXME: 2022/5/12 double类型构造，可能得不到想要的结果
        BigDecimal bd4 = new BigDecimal(1.0);
        // 输出true
        log.info("bd3和bd4相等：{}", bd3.equals(bd4));

        BigDecimal bd5 = new BigDecimal("1.00");
        BigDecimal bd6 = new BigDecimal("1.0");
        // 输出false：值和精度相等，才认为是相等
        log.info("bd5和bd6相等：{}", bd5.equals(bd6));
    }
}
