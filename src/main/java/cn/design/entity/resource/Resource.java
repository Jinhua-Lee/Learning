package cn.design.entity.resource;

import cn.design.entity.EntitySupport;

/**
 * 【资源】<p>&emsp;
 * 1. 可以是用户自定义的页面模块资源；<p>&emsp;
 * 2. 也包括权限服务自身的实体【用户】【角色】等
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/22 16:27
 */
public interface Resource extends EntitySupport, ResourceTypeCapable {
}
