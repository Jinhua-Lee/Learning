package cn.designpattern.factory.normal;

/**
 * Todo
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
