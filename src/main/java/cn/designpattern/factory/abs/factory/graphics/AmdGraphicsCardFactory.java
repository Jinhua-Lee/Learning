package cn.designpattern.factory.abs.factory.graphics;

import cn.designpattern.factory.product.graphicscard.AmdGraphicsCard;
import cn.designpattern.factory.product.graphicscard.GraphicsCard;

/**
 * 抽象产品B的工厂的实现 -> AMD显卡工厂
 *
 * @author Jinhua
 * @date 2021/2/23 0:11
 */
public class AmdGraphicsCardFactory implements GraphicsCardFactory {
    @Override
    public GraphicsCard createGraphicsCard() {
        return new AmdGraphicsCard();
    }
}
