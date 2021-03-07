package cn.designpattern.strategy;

import lombok.Setter;

/**
 * 算法执行的上下文环境
 *
 * @author Jinhua
 * @date 2021/3/7下午8:54
 */
public class Context {

    private final Integer a;
    private final Integer b;

    @Setter
    private CalculateStrategy strategy;

    public Context(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public Context(Integer a, Integer b, CalculateStrategy strategy) {
        this.a = a;
        this.b = b;
        this.strategy = strategy;
    }

    /**
     * 提供Strategy对策略访问的方法
     *
     * @return 策略执行结果
     */
    public Integer getResult() {
        return strategy.calculate(this.a, this.b);
    }

    public static void main(String[] args) {
        // 1. 普通方式
        Context context = new Context(4, 5);

        // 1.1 设置策略【加】 => 9
        context.setStrategy(new AddStrategy());
        System.out.println(context.getResult());

        // 1.2 更改策略【减】 => -1
        context.setStrategy(new SubStrategy());
        System.out.println(context.getResult());

        // 2. 枚举方式，更改策略【乘】 => 20
        context.setStrategy(CalculateStrategyEnum.MULTI);
        System.out.println(context.getResult());

    }
}
