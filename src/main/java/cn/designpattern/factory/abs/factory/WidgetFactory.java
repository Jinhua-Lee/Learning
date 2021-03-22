package cn.designpattern.factory.abs.factory;

import cn.designpattern.factory.product.cpu.Cpu;
import cn.designpattern.factory.product.graphicscard.GraphicsCard;

/**
 * 产品工厂 -> 生产CPU和显卡
 *
 * @author Jinhua
 * @date 2021/2/23 22:20
 */
public interface WidgetFactory {

    /**
     * 生产CPU
     *
     * @return CPU
     */
    Cpu createCpu();

    /**
     * 生产显卡
     *
     * @return 生产显卡
     */
    GraphicsCard createGraphicsCard();
}
