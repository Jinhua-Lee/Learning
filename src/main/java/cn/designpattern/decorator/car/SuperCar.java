package cn.designpattern.decorator.car;

import cn.designpattern.decorator.car.concreteCar.FlyCar;
import cn.designpattern.decorator.car.concreteCar.WaterCar;

/**
 * 超级汽车 -> 增强的功能定义
 *
 * @author Jinhua
 */
public abstract class SuperCar implements ICar {

    /**
     * 拿到有基本功能汽车的引用
     */
    private final ICar car;

    public SuperCar(ICar car) {
        this.car = car;
    }

    /**
     * 此处任然是移动功能，未进行方法增强
     */
    @Override
    public void move() {
        car.move();
    }

    public static void main(String[] args) {
        System.out.println("基本功能");
        ICar iCar = new Car();
        iCar.move();

        System.out.println("---------------");
        System.out.println("增加新的功能：飞行");
        ICar flyCar = new FlyCar(iCar);
        flyCar.move();

        System.out.println("---------------");
        System.out.println("增加新的功能：水里游");
        ICar waterCar = new WaterCar(iCar);
        waterCar.move();

        System.out.println("---------------");
        System.out.println("增加两个新的功能，飞行，水里游");
        ICar waterCar2 = new WaterCar(new FlyCar(iCar));
        waterCar2.move();
    }
}
