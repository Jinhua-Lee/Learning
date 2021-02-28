package cn.designpattern.factory.normal;

/**
 * 工厂方法模式主方法
 *
 * @author Jinhua
 * @date 2021/2/28下午11:38
 */
public class NormalFactoryMain {

    public static void main(String[] args) {
        CarFactory factory = new BmwFactory();
        Car car = factory.createCar();
        System.out.println(car.getName());
        car.drive();
    }
}
