package cn.designpattern.factory.normal;

/**
 * 具体工厂 -> 宝马汽车工厂
 *
 * @author Jinhua
 * @date 2021/2/28下午11:37
 */
public class BmwFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Bmw();
    }
}
