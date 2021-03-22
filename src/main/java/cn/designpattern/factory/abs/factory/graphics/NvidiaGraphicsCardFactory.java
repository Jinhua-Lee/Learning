package cn.designpattern.factory.abs.factory.graphics;

import cn.designpattern.factory.product.graphicscard.GraphicsCard;
import cn.designpattern.factory.product.graphicscard.NvidiaGraphicsCard;

/**
 * 抽象产品B的工厂的实现 -> 英伟达显卡工厂
 *
 * @author Jinhua
 * @date 2021/2/23 0:01
 */
public class NvidiaGraphicsCardFactory implements GraphicsCardFactory {
    @Override
    public GraphicsCard createGraphicsCard() {
        return new NvidiaGraphicsCard();
    }
}
