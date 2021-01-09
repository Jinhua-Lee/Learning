package cn.designpattern.decorator.car.concreteCar;

import cn.designpattern.decorator.car.ICar;
import cn.designpattern.decorator.car.SuperCar;

/**
 * AI智能汽车类
 *
 * @author Jinhua
 */
public class AiCar extends SuperCar {

    /**
     * 继续拿到抽象类的基本功能汽车引用，构造其属性
     * @param car 基本功能的汽车
     */
    public AiCar(ICar car) {
        super(car);
    }

    public void autoMove() {
        System.out.println("自动跑");
    }

    /**
     * 对移动方法进行了增强
     */
    @Override
    public void move() {
        super.move();
        autoMove();
    }
}
