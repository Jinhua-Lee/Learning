package cn.designpattern.decorator.car.concreteCar;

import cn.designpattern.decorator.car.ICar;
import cn.designpattern.decorator.car.SuperCar;

/**
 * 实现天上飞功能的超级汽车类
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
