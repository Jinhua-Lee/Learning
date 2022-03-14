package cn.designpattern.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Consumer;

/**
 * 单个元素, 不支持添加
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/14 21:57
 */
@Getter
@AllArgsConstructor
@ToString
public class Leaf implements Component {

    private String name;

    @Override
    public void add(Component component) throws NotImplementedException {
    }

    @Override
    public void remove(Component component) throws NotImplementedException {
    }

    @Override
    public void eachChild(Consumer<Component> traverseConsumer) {
        traverseConsumer.accept(this);
    }
}
