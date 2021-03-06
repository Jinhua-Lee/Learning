package cn.designpattern.factory.abs.factory.graphics;

import cn.designpattern.factory.product.graphicscard.GraphicsCard;

/**
 * 抽象产品B的工厂 -> 显卡工厂
 *
 * @author Jinhua
 * @date 2021/2/22 23:52
 */
public interface GraphicsCardFactory {

    /**
     * 生产显卡的方法
     *
     * @return 显卡
     */
    GraphicsCard createGraphicsCard();
}
