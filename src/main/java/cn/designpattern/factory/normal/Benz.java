package cn.designpattern.factory.normal;

/**
 * 具体产品 -> 奔驰汽车
 *
 * @author Jinhua
 * @date 2021/2/28下午11:25
 */
public class Benz implements Car {
    @Override
    public String getName() {
        return "Benz";
    }

    @Override
    public void drive() {
        System.out.println("驾驶Benz");
    }
}
