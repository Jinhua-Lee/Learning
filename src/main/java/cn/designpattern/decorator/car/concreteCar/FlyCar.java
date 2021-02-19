package cn.designpattern.decorator.car.concreteCar;

import cn.designpattern.decorator.car.ICar;
import cn.designpattern.decorator.car.SuperCar;

/**
 * 天上飞功能的超级汽车 -> 增强功能的实现
 *
 * @author Jinhua
 */
public class FlyCar extends SuperCar {

    public FlyCar(ICar car) {
        super(car);
    }

    public void fly() {
        System.out.println("天上飞");
    }

    @Override
    public void move() {
        super.move();
        fly();
    }
}
