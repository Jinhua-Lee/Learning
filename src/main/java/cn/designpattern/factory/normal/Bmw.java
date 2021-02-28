package cn.designpattern.factory.normal;

/**
 * 具体产品 -> 宝马
 *
 * @author Jinhua
 * @date 2021/2/28下午11:26
 */
public class Bmw implements Car {
    @Override
    public String getName() {
        return "Bmw";
    }

    @Override
    public void drive() {
        System.out.println("驾驶Bmw");
    }
}
