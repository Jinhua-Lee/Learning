package com.se.eq;

import java.math.BigDecimal;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2022/2/16 14:09
 */
public class BigDecimalEquals {

    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal(1);
        BigDecimal bigDecimal2 = new BigDecimal(1);
        // 输出true
        System.out.println(bigDecimal1.equals(bigDecimal2));

        BigDecimal bigDecimal3 = new BigDecimal(1);
        BigDecimal bigDecimal4 = new BigDecimal(1.0);
        // 输出true
        System.out.println(bigDecimal3.equals(bigDecimal4));

        BigDecimal bigDecimal5 = new BigDecimal("1.00");
        BigDecimal bigDecimal6 = new BigDecimal("1.0");
        // 输出false：值和精度相等，才认为是相等
        System.out.println(bigDecimal5.equals(bigDecimal6));
    }
}
