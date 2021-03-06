package cn.designpattern.builder.normal;

import cn.designpattern.builder.product.*;
import cn.designpattern.factory.product.cpu.AppleCpu;
import cn.designpattern.factory.product.graphicscard.AmdGraphicsCard;

/**
 * 具体构建者 -> 电脑构建者<p>
 * 目前用一个实现
 *
 * @author Jinhua
 * @date 2021/3/2下午10:49
 */
public class ConcreteComputerBuilder extends AbstractComputerBuilder {

    public ConcreteComputerBuilder() {
        super();
        super.computer = new Computer();
    }

    @Override
    public void buildMaster() {
        super.computer.setMaster(new Master("linux 服务主机", new AppleCpu(), new AmdGraphicsCard()));
    }

    @Override
    public void buildScreen() {
        super.computer.setScreen(new Screen());
    }

    @Override
    public void buildKeyboard() {
        super.computer.setKeyboard(new Keyboard());
    }

    @Override
    public void buildMouse() {
        super.computer.setMouse(new Mouse());
    }
}
