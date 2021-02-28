package cn.designpattern.factory.normal;

/**
 * 抽象产品 -> 汽车
 *
 * @author Jinhua
 * @date 2021/2/28下午11:23
 */
public interface Car {

    /**
     * 获取名字
     *
     * @return 名字
     */
    String getName();

    /**
     * 驾驶方法
     */
    void drive();
}
