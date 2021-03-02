package cn.designpattern.builder;

import cn.designpattern.builder.product.Keyboard;
import cn.designpattern.builder.product.Master;
import cn.designpattern.builder.product.Mouse;
import cn.designpattern.builder.product.Screen;

/**
 * 具体构建者 -> 电脑构建者<p>
 * 目前用一个实现
 *
 * @author Jinhua
 * @date 2021/3/2下午10:49
 */
public class ConcreteComputerBuilder extends AbstractComputerBuilder {

    @Override
    public void buildMaster() {
        super.computer.setMaster(new Master());
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
