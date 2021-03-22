package cn.designpattern.factory.product.graphicscard;

/**
 * 抽象产品B的实现 -> AMD 显卡
 *
 * @author Jinhua
 * @date 2021/2/23 20:21
 */
public class AmdGraphicsCard implements GraphicsCard {
    @Override
    public String getName() {
        return "AMD Graphics Card";
    }
}
