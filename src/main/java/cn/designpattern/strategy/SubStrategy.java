package cn.designpattern.strategy;

/**
 * 减法策略
 *
 * @author Jinhua
 * @date 2021/3/7下午8:53
 */
public class SubStrategy implements CalculateStrategy {

    @Override
    public Integer calculate(Integer a, Integer b) {
        return a - b;
    }
}
