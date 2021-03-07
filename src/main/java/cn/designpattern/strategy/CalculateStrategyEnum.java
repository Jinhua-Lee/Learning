package cn.designpattern.strategy;

/**
 * 枚举方式扩展策略
 *
 * @author Jinhua
 * @date 2021/3/7下午9:36
 */
public enum CalculateStrategyEnum implements CalculateStrategy {

    /**
     * 加法策略
     */
    ADD {
        @Override
        public Integer calculate(Integer a, Integer b) {
            return a + b;
        }
    },

    /**
     * 减法策略
     */
    SUB {
        @Override
        public Integer calculate(Integer a, Integer b) {
            return a - b;
        }
    },

    /**
     * 乘法策略
     */
    MULTI {
        @Override
        public Integer calculate(Integer a, Integer b) {
            return a * b;
        }
    };

    @Override
    public abstract Integer calculate(Integer a, Integer b);

}
