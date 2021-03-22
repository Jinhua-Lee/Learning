package cn.designpattern.factory.product.graphicscard;

/**
 * 抽象产品B的实现 -> 英伟达显卡
 *
 * @author Jinhua
 * @date 2021/2/23 20:24
 */
public class NvidiaGraphicsCard implements GraphicsCard {
    @Override
    public String getName() {
        return "Nvidia Graphics Card";
    }
}
