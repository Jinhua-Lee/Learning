package cn.designpattern.composite;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 形成的组合
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/14 21:58
 */
@Getter
public class Composite implements Component {

    private final List<Component> components = new ArrayList<>();

    @Override
    public void add(Component component) {
        this.components.add(component);
    }

    @Override
    public void remove(Component component) {
        this.components.remove(component);
    }

    @Override
    public void eachChild(Consumer<Component> traverseConsumer) {
        this.components.forEach(component -> component.eachChild(traverseConsumer));
    }
}
