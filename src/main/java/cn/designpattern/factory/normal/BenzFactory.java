package cn.designpattern.factory.normal;

/**
 * 具体工厂 -> 奔驰汽车工厂
 *
 * @author Jinhua
 * @date 2021/2/28下午11:36
 */
public class BenzFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Benz();
    }
}
