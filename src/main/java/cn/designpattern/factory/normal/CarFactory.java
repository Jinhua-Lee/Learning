package cn.designpattern.factory.normal;

/**
 * 汽车工厂的定义
 *
 * @author Jinhua
 * @date 2021/2/28下午11:27
 */
public interface CarFactory {

    /**
     * 制造汽车
     *
     * @return 汽车
     */
    Car createCar();
}
