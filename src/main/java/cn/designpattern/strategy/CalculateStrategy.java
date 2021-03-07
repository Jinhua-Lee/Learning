package cn.designpattern.strategy;

/**
 * 策略的接口
 *
 * @author Jinhua
 * @date 2021/3/7下午8:49
 */
public interface CalculateStrategy {

    /**
     * 对两个数的操作策略
     *
     * @param a a
     * @param b b
     * @return 计算结果
     */
    Integer calculate(Integer a, Integer b);
}
