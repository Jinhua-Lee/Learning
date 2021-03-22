package cn.designpattern.factory.abs;

import cn.designpattern.factory.abs.factory.AppleWidgetFactory;
import cn.designpattern.factory.abs.factory.WindowsWidgetFactory;
import cn.designpattern.factory.abs.integrated.MacBook;
import cn.designpattern.factory.abs.integrated.PcProduct;
import cn.designpattern.factory.abs.integrated.WindowsSurface;

/**
 * 抽象工厂模式演示【主方法】
 * <p>
 * 其中product包下为产品的组装，产品集成类的空参构造方法中有抽象工厂的应用
 *
 * @author Jinhua
 * @date 2021/2/23 20:50
 */
public class AbsFactoryMain {

    public static void main(String[] args) {

        PcProduct windowsSurface = new WindowsSurface(new WindowsWidgetFactory());
        System.out.println(windowsSurface);

        PcProduct macBook = new MacBook(new AppleWidgetFactory());
        System.out.println(macBook);
    }
}
