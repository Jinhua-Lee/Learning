package cn.designpattern.strategy;

/**
 * 加法策略
 *
 * @author Jinhua
 * @date 2021/3/7下午8:51
 */
public class AddStrategy implements CalculateStrategy {

    @Override
    public Integer calculate(Integer a, Integer b) {
        return a + b;
    }
}
