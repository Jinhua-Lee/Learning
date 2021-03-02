package cn.designpattern.builder;

import cn.designpattern.builder.product.Computer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * 抽象建造者 -> 电脑Builder
 *
 * @author Jinhua
 * @date 2021/3/2下午10:30
 */
public abstract class AbstractComputerBuilder {

    /**
     * 待构建的复杂对象 -> 电脑
     */
    @Getter
    protected Computer computer;

    /**
     * 构建主机
     */
    public abstract void buildMaster();

    /**
     * 构建显示器
     */
    public abstract void buildScreen();

    /**
     * 构建键盘
     */
    public abstract void buildKeyboard();

    /**
     * 构建鼠标
     */
    public abstract void buildMouse();

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
