package cn.design.entity.resource;

import lombok.Getter;

/**
 * 【资源抽象】
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/16 11:26
 */
@Getter
public abstract class BaseResource implements Resource, ResourceTypeCapable {

    protected Integer id;
}
