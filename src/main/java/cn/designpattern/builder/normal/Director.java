package cn.designpattern.builder.normal;

import cn.designpattern.builder.product.Computer;
import lombok.AllArgsConstructor;

/**
 * 指导创建者<p>
 * 个人基于URIBuilder理解：如果所指挥的逻辑不复杂，可优化为Builder类的一个方法
 *
 * @author Jinhua
 * @date 2021/3/2下午10:44
 */
@AllArgsConstructor
public class Director {

    /**
     * 构造方法传入创建者对象
     */
    private final AbstractComputerBuilder computerBuilder;

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
