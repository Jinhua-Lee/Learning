package cn.designpattern.builder;

import cn.designpattern.builder.product.Computer;
import lombok.Setter;

/**
 * 指导创建者
 *
 * @author Jinhua
 * @date 2021/3/2下午10:44
 */
public class Director {

    @Setter
    private AbstractComputerBuilder computerBuilder;

    public void build() {
        computerBuilder.buildMaster();
        computerBuilder.buildMouse();
        computerBuilder.buildScreen();
        computerBuilder.buildKeyboard();
    }

    public Computer getComputer() {
        return computerBuilder.getComputer();
    }
}
