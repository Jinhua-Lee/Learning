package com.se.stream;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 提供Pi的计算方法，供延迟计算
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/25 17:28
 */
class PiSupplier implements Supplier<Double> {

    double sum = 0.0;
    double current = 1.0;
    boolean sign = true;

    @Override
    public Double get() {
        sum += (sign ? 4 : -4) / this.current;
        this.current = this.current + 2.0;
        this.sign = !this.sign;
        return sum;
    }

    public static void main(String[] args) {
        Stream<Double> piStream = Stream.generate(new PiSupplier());
        piStream.skip(100).limit(10)
                .forEach(System.out::println);
    }
}
